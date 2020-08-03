package com.web.blog.service.file;

import javax.mail.Multipart;

import org.springframework.web.multipart.MultipartFile;

import com.web.blog.model.user.User;

public interface FileService {

	public boolean uploadProfileImage(String email, MultipartFile imageFile);

}
