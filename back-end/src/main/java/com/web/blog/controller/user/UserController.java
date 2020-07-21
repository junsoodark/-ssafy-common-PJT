package com.web.blog.controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;
import com.web.blog.service.JwtService;
import com.web.blog.service.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
public class UserController {

    @Autowired
    UserDao userDao;
    
    @Autowired
    UserService userService;
    
    @Autowired
    private JwtService jwtService;

	@GetMapping("/account/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);

		ResponseEntity response = null;

        if (userOpt.isPresent()) {
        	String token = jwtService.generateToken(email);
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.data = "success";
            result.object = token;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

		return response;
	}

	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기")
	public Object signup(@Valid @RequestBody SignupRequest request) {
		// 이메일, 닉네임 중복처리 필수
		// 회원가입단을 생성해 보세요.
		userService.join(request);
		final BasicResponse result = new BasicResponse();
		
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/account/userinfo")
	@ApiOperation(value = "회원정보 읽기")
	public Object userinfo(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {
		ResponseEntity response = null;
		
		Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);
		
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			Map<String, String> map = new HashMap<>();
			map.put("email", 	user.getEmail());
			map.put("nickname", user.getNickname());
			
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			result.object = map;
			
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		else response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		return response;
	}
	
	@PostMapping("/account/modify")
	@ApiOperation(value = "회원정보 수정")
	public Object modify(@Valid @RequestBody SignupRequest request) {
		Optional<User> userOpt = userDao.findUserByEmail(request.getEmail());
		userOpt.ifPresent(user ->{
			user.setPassword(request.getPassword());
			user.setNickname(request.getNickname());
			userDao.save(user);
		});
		
		final BasicResponse result = new BasicResponse();
		
		result.status = true;
		result.data = "success";
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/account/delete")
	@ApiOperation(value = "탈퇴하기")
	public Object delete(final String email, final String password) {
		Optional<User> userDel = userDao.findUserByEmail(email);
		if (userDel.isPresent()) {
			User user = userDel.get();
			userDao.delete(user);
			final BasicResponse result = new BasicResponse();

			result.status = true;
			result.data = "success";

			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}