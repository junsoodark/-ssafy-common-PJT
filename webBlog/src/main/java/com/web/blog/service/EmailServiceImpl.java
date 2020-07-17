package com.web.blog.service;

import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.web.blog.dao.user.EmailDao;
import com.web.blog.model.email.SignUpEmail;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	EmailDao emailDao;

	@Autowired
	JavaMailSender mailSender;

	@Transactional
	@Override
	public SignUpEmail sendEmailVerification(String email) {
		System.out.println(email);
		// TODO Auto-generated method stub
		Optional<SignUpEmail> emailInfo = emailDao.findSignUpEmailByEmail(email);
		Random r = new Random();
		String code = Integer.toString(r.nextInt(4589362) + 49311);
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.gmail.com");
		sender.setPort(587);
		sender.setUsername("ssafyweb@gmail.com");
		sender.setPassword("3rltjdnf06");
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		sender.setJavaMailProperties(prop);
		MimeMessage message = sender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(email);
			helper.setText("가입 코드 : " + code);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sender.send(message);

		if (emailInfo.isPresent()) {
			SignUpEmail se = emailInfo.get();
			se.setEmail(email);
			se.setCode(code);
			emailDao.save(se);
			return se;
		} else {
			SignUpEmail se = new SignUpEmail();
			se.setEmail(email);
			se.setCode(code);
			emailDao.save(se);
			return se;
		}

	}

	@Transactional
	@Override
	public SignUpEmail check(String email, String code) {
		return emailDao.findSignUpEmailByEmailAndCode(email, code);

	}

}
