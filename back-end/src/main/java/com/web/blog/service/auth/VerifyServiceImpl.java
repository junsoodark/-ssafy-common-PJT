package com.web.blog.service.auth;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web.blog.dao.auth.VerifyDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.auth.Mail;
import com.web.blog.model.auth.Verify;

@Service
public class VerifyServiceImpl implements VerifyService {
	@Value("${verify.lenCode}")
	private int lenCode;
	
	@Autowired
	private VerifyDao verifyDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public boolean isDuplicated(String email) {
		return userDao.findUserByEmail(email).isPresent();
	}

	@Override
	public boolean isValid(String email, String code) {
		return verifyDao.findVerifyByEmailAndCode(email, code).isPresent();
	}

	@Override
	public Mail generateVerifyMail(String email) {
		String code = generateCode();
		verifyDao.save(new Verify(email, code));
		
		Mail mail = new Mail();
		mail.setTo(email);
		mail.setTitle("[SSAFY Web Blog] - verify your email");
		mail.setMessage(code);
		return mail;
	}
	
	public String generateCode() {
		Random random = new Random(System.currentTimeMillis());
		int range = (int)Math.pow(10, lenCode);
		int trim  = (int)Math.pow(10, lenCode-1);
		int code = random.nextInt(range) + trim;
		code = code > range ? code-trim : code;
		return String.valueOf(code);
	}
}
