package com.web.blog.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.blog.model.email.SignUpEmail;
@Service
public interface EmailService {

	SignUpEmail sendEmailVerification(String email); 
	SignUpEmail check(String email, String key);

}
