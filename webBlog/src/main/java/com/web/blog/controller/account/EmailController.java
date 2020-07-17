package com.web.blog.controller.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.email.SignUpEmail;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;
import com.web.blog.service.EmailService;
import com.web.blog.service.JwtService;
import com.web.blog.service.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })
@RequestMapping("/email")
@RestController
public class EmailController {

    
    @Autowired
    EmailService emailService;
    
	@RequestMapping(path = "/signup/send", method=RequestMethod.POST)
	private Object sendEmailVerification(@RequestParam(required = true) final String email) {
		System.out.println("?????");
		SignUpEmail se = emailService.sendEmailVerification(email);
		
		if(se==null) {
			return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}else return  new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/signup/check", method=RequestMethod.POST)
	@ResponseBody
	private Object checkEmailVerification(@RequestParam(required = true) final String email, @RequestParam(required = true) final String code) {
		SignUpEmail se = emailService.check(email,code);
		if(se==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}else return  new ResponseEntity<>(null, HttpStatus.OK);
			
	}
	
	
}