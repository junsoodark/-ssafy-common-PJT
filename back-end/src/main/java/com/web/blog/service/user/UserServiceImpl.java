package com.web.blog.service.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.web.blog.dao.user.UserDao;
import com.web.blog.model.user.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public User findUserByEmail(final String email) {
		Optional<User> userOpt = userDao.findUserByEmail(email);
		return userOpt.isPresent() ? userOpt.get() : null;
	}

	@Override
	public User findUserById(int id) {
		Optional<User> userOpt = userDao.findUserById(id);
		return userOpt.isPresent() ? userOpt.get() : null;
	}

	@Override
	public Map<String, Object> User2Map(User user) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("email", user.getEmail());
		ret.put("name", user.getName());
		ret.put("age", user.getAge());
		ret.put("sex", user.getSex());
		return ret;
	}

	@Override
	public Map<String, String> getErrorMessage(final Errors errors) {
		Map<String, String> ret = new HashMap<>();
		for (FieldError error : errors.getFieldErrors())
			ret.put(error.getField(), error.getDefaultMessage());
		return ret;
	}

	@Override
	public boolean create(final User user) {
		if (userDao.findUserByEmail(user.getEmail()).isPresent())
			return false;
		userDao.save(user);
		return true;
	}

	@Override
	public boolean update(final User user) {
		Optional<User> userOpt = userDao.findUserByEmail(user.getEmail());
		if (userOpt.isPresent() == false)
			return false;

		userOpt.ifPresent(u -> {
			u.setPassword(user.getPassword());
			u.setName(user.getName());
			u.setAge(user.getAge());
			u.setSex(user.getSex());
			userDao.save(u);
		});

		return true;
	}

	@Override
	public boolean delete(final String email) {
		Optional<User> userOpt = userDao.findUserByEmail(email);
		if (userOpt.isPresent() == false)
			return false;

		userOpt.ifPresent(user -> {
			userDao.delete(user);
		});
		return true;
	}
}
