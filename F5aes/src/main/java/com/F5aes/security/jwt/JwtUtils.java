package com.F5aes.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {
	@Value("${jwt.secret.key}")
	private String secretKey;
	@Value("${jwt.time.expiration}")
	private String timeExpiration;
	public String generateAccessToken(String email){


		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() +Long.parseLong(timeExpiration)))
				.signWith(getSignatureKye(), SignatureAlgorithm.HS256)
				.compact();
	}
// Validate access token
	public  boolean isTokenValid(String token){
		try{

			Jwts.parserBuilder().
					setSigningKey(getSignatureKye())
					.build().parseClaimsJwt(token)
					.getBody();
			return  true;
		}catch (Exception e){
			System.out.println("Invalid Token,error: "+ e.getMessage() );
			return  false;
		}
	}

	// Obtain username from token
	public  String getUsernameFromToken(String token){
		return  getClaim(token,Claims::getSubject);
	}

	// Obtain only one claim
	public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
		Claims claims = extractAllClaims(token);

		return  claimsTFunction.apply(claims);
	}
	// Obtain claims from token
	public Claims extractAllClaims(String token){
		return  Jwts.parserBuilder()
				.setSigningKey(getSignatureKye()).build().parseClaimsJwt(token).getBody();
	}
	// get signature
	public Key getSignatureKye(){

		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
