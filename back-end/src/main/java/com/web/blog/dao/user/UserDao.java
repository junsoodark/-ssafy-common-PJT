
package com.web.blog.dao.user;

import java.util.Optional;

import com.web.blog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	Optional<User> findUserByEmail(String email);
	Optional<User> findUserByEmailAndPassword(String email, String password);
}
