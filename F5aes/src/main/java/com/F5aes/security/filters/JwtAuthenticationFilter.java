package com.F5aes.security.filters;

import com.F5aes.model.UserModel;
import com.F5aes.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final JwtUtils jwtUtils;

	public JwtAuthenticationFilter(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
	                                            HttpServletResponse response) throws AuthenticationException {

		UserModel userModel = null;
		String firstName = "";
		String	password = "";
		try{
			userModel = new ObjectMapper().readValue(request.getInputStream(),UserModel.class);
			firstName = userModel.getFirstName();
			password = userModel.getPassword();

			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(firstName, password);
			return getAuthenticationManager().authenticate(authenticationToken);

		}catch (IOException e){
			throw new RuntimeException(e + "Username or Password is not correct please try again later");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
	                                        HttpServletResponse response, FilterChain chain
	,Authentication authResult) throws IOException , ServletException{
		User user = (User) authResult.getPrincipal();
		String token = jwtUtils.generateAccessToken(user.getUsername());
		response.addHeader("Authorization", token);

		Map<String,Object> httpResponse = new HashMap<>();
		httpResponse.put("token",token);
		httpResponse.put("message","Authentication is Passed Successfully");
		httpResponse.put("Username",user.getUsername());

		response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().flush();
	}
}
