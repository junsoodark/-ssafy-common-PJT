package com.web.blog.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class PasswordEncoding implements PasswordEncoder{
	private static PasswordEncoding instance = new PasswordEncoding();
	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static PasswordEncoding getInstance() {
		return instance;
	}
	
	@Override
	public String encode(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
