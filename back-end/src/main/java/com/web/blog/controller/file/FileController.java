package com.web.blog.controller.file;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.web.blog.model.user.User;
import com.web.blog.service.file.FileService;

import io.swagger.annotations.ApiOperation;

@Controller
public class FileController {
	
	
	@Autowired
	FileService fileService;
	
	@PostMapping("/user/details/image")
	@ApiOperation("유저 프로필 이미지를 업로드합니다.")
	ResponseEntity uploadProfileImage(@RequestParam String email, @RequestPart MultipartFile imageFile)
	{
		System.out.println("??");
		if(!fileService.uploadProfileImage(email,imageFile)) return new ResponseEntity("업로드에 실패했습니다.",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		else return new ResponseEntity("이미지 업로드에 성공했습니다.", HttpStatus.OK);
	}
	
	
}
