package com.web.blog.service.user;

import java.util.Map;

import org.springframework.validation.Errors;

import com.web.blog.model.user.User;

public interface UserService {
	public User findUserByEmail(final String email);
	public Map<String, Object> User2Map(final User user);
	public User findUserById(int id);
	public boolean create(final User user);
	public boolean update(final User user);
	public boolean delete(final String email);
	
	public Map<String, String> getErrorMessage(final Errors errors);
}
