package com.retail.ecom.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${myapp.jwt.secret}")
	private String secret;

	@Value("${myapp.jwt.access.expiration}")
	private long accessExpiry;

	@Value("${myapp.jwt.refresh.expiration}")
	private long refreshExpiry;

	public String genaretAccessToken(String userName,String role) {

		return generateToken(userName,role, accessExpiry);
	}

	public String genaretRefreshToken(String userName ,String role) {

		return generateToken(userName,role, refreshExpiry);
	}

	private String generateToken(String userName,String role, long expiration) {
		return Jwts.builder().setClaims(Maps.of("role", role).build())
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				
				.signWith(getSignatureKey(), SignatureAlgorithm.HS256).compact();
	}
	
	public String getUserRole (String token) {
		return parseClaims(token).get("role",String.class);
		
		
	}

	private Key getSignatureKey() {

		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}
	public String getUsername(String jwtToken) {
		return parseClaims(jwtToken).getSubject();
	}
	private Claims parseClaims(String jwtToken) {
		 return  Jwts.parserBuilder().setSigningKey(getSignatureKey()).build().parseClaimsJwt(jwtToken).getBody();
	}
	
	
	
	
	
	
	
	
	
	
	

}