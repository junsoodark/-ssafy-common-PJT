
package com.web.blog.dao.user;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
	User getUserByEmail(String email);

	Optional<User> findUserByEmailAndPassword(String email, String password);

	Optional<User> findUserByEmail(String email);
	
	List<User> findAll();

	User findUserByUidAndPassword(String uid, String password);

	User getUserByUid(String uid);

	void delete(User user);

	// update or insert
	User save(User save);

}
