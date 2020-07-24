package com.web.blog.service;

import java.util.Map;

import org.springframework.validation.Errors;

import com.web.blog.model.user.User;

public interface UserService {
	public Map<String, Object> findUserByEmail(final String email);
	public Map<String, String> getErrorMessage(final Errors errors);
	public boolean create(final User user);
	public boolean update(final User user);
	public boolean delete(final String email);
}
