package com.web.blog.service.auth;

import com.web.blog.model.auth.Mail;

public interface VerifyService {
	public boolean isDuplicated(String email);
	public boolean isValid(String email, String code);
	public Mail generateVerifyMail(String email);
}
