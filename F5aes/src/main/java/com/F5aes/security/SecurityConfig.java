package com.F5aes.security;
import com.F5aes.security.filters.JwtAuthenticationFilter;
import com.F5aes.security.filters.JwtAuthorizationFilter;
import com.F5aes.security.jwt.JwtUtils;
import com.F5aes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
@Configuration
public class SecurityConfig {
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private UserService userService;
	@Autowired
	JwtAuthorizationFilter authorizationFilter;
@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource(){
	CorsConfiguration configuration = new CorsConfiguration();
	configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
	configuration.setAllowedMethods(Arrays.asList("GET","PUT","DELETE","POST","OPTIONS"));
	configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type","X-Requested-With","Accept"));

	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

	source.registerCorsConfiguration("/**", configuration);

return  source;
}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");

		return httpSecurity
				.cors(Customizer.withDefaults())
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/Images/**").permitAll();
					auth.requestMatchers("/api/**").permitAll();
					auth.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
					auth.anyRequest().authenticated();
				})
				.sessionManagement(session -> {
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
		AuthenticationManagerBuilder authBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder);
		return authBuilder.build();
	}

}


