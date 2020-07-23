package com.web.blog.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.blog.service.auth.MailService;
import com.web.blog.service.auth.VerifyService;

import io.swagger.annotations.ApiOperation;

@Controller
public class AuthController {
	@Autowired
	private VerifyService verifyService;
	
	@Autowired
	private MailService mailService;
	
	@PostMapping("/verify")
	@ApiOperation(value="이메일을 입력받아 사용중인 이메일인지 확인하고, 사용중이지 않다면 인증 메일을 전송합니다.")
	public ResponseEntity sendVerifyMail(@RequestParam final String email) {
		if(verifyService.isDuplicated(email)) return new ResponseEntity("이미 사용중인 이메일 입니다.", HttpStatus.CONFLICT);
		
		mailService.sendMail(verifyService.generateVerifyMail(email));
		return new ResponseEntity("인증 메일을 전송했습니다.", HttpStatus.OK);
	}
}
