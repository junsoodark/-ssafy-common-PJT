package com.web.blog.service;

import java.sql.SQLException;
import java.util.List;

import com.web.blog.model.user.User;

public interface UserService {
	public User login(String uid, String upw) throws SQLException;

	public void join(User user);

	public void update(User user);

	public void delete(String uid);

	public User search(String uid);

	public List<User> searchAll();

}
