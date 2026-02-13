package com.eureca.sequrity;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtSecurity {
	
	private final String SECRET = "mySecretKeyForJwtTokenGeneration83499746681";

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).claim("role", "ADMIN	").setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))		//expire in 15 min
				.signWith(getKey(), SignatureAlgorithm.HS256).compact();
	}
	
	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
}
