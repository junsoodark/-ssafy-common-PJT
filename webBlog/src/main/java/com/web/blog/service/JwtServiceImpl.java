package com.web.blog.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expmin}")
	private Long expMin;
	
	@Override
	public String generateToken(final String id) {
		Claims claims = Jwts.claims().setSubject(id);
		Date now = new Date();
		return Jwts.builder()
				   .setHeaderParam("typ", "JWT")
				   .setClaims(claims)
				   .setIssuedAt(now)
				   .setExpiration(new Date(now.getTime() + expMin*60*1000L))
				   .signWith(SignatureAlgorithm.HS256, secret)
				   .compact();
	}
	@Override
	public boolean validCheck(final String token, final String id) throws Exception{
		Claims claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject().equals(id);
	}
}
