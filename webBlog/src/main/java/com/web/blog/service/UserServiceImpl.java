package com.web.blog.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.user.UserDao;
import com.web.blog.model.user.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User login(String uid, String upw) throws SQLException {
		return userDao.findUserByUidAndPassword(uid, upw);
	}

	@Override
	public void join(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.save(user);

	}

	@Override
	public void delete(String uid) {
		userDao.delete(userDao.getUserByUid(uid));
	}

	@Override
	public User search(String uid) {
		return userDao.getUserByUid(uid);
	}

	@Override
	public List<User> searchAll() {
		return userDao.findAll();
	}

}
