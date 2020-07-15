package com.web.blog.service;

public interface JwtService {
	public String generateToken(final String id);
	public boolean validCheck(final String token, final String id) throws Exception;
}
