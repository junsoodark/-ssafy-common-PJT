package com.web.blog.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.blog.service.auth.JwtService;
import com.web.blog.service.auth.MailService;
import com.web.blog.service.auth.VerifyService;

import io.swagger.annotations.ApiOperation;

@Controller
public class AuthController {
	@Autowired
	private VerifyService verifyService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/verify")
	@ApiOperation(value="이메일을 입력받아 사용중인 이메일인지 확인하고, 사용중이지 않다면 인증 메일을 전송합니다.")
	public ResponseEntity sendVerifyMail(@RequestParam final String email) {
		if(verifyService.isDuplicated(email)) return new ResponseEntity("이미 사용중인 이메일 입니다.", HttpStatus.CONFLICT);
		
		mailService.sendMail(verifyService.generateVerifyMail(email));
		return new ResponseEntity("인증 메일을 전송했습니다.", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	@ApiOperation(value="이메일과 비밀번호를 입력받아 사용자 정보를 검증하고, 유효하다면 토큰을 반환합니다.")
	public ResponseEntity login(@RequestParam final String email, @RequestParam final String password) {
		if(verifyService.isValidUser(email, password)==false) return new ResponseEntity("일치하는 사용자가 없습니다. 이메일 또는 비밀번호를 확인해주세요.", HttpStatus.NOT_FOUND);
		return new ResponseEntity(jwtService.generateToken(email), HttpStatus.OK);
	}
	
	@PostMapping("/login/valid")
	@ApiOperation(value="이메일과 토큰을 입력받아 사용자 정보를 검증하고 결과를 반환합니다.")
	public ResponseEntity isValidToken(@RequestParam final String email, @RequestParam final String token) {
		try {
			if(jwtService.isValidToken(token, email)==false)
				return new ResponseEntity("유효하지 않은 토큰입니다.", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity("유효하지 않은 토큰입니다.", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity("토큰 인증에 성공했습니다.", HttpStatus.OK);
	}
}
