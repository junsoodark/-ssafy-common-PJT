package com.web.blog.service.auth;

public interface JwtService {
	public String generateToken(final String email);
	public boolean isValidToken(final String token, final String email) throws Exception;
}
