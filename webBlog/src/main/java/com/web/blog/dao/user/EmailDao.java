package com.web.blog.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.blog.model.email.SignUpEmail;

public interface EmailDao extends JpaRepository<SignUpEmail, String>{
	SignUpEmail save(SignUpEmail s);
	
	SignUpEmail findSignUpEmailByEmailAndCode(String email, String code);

	Optional <SignUpEmail> findSignUpEmailByEmail(String email);
}
