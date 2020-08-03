package com.web.blog.service.file;

import javax.mail.Multipart;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.user.User;

@Transactional
@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private UserDao userDao;
	
//	private final String baseUrl = "/home/ubuntu/";
	private final String baseUrl = "C:\\Users\\multicampus\\resource\\profileImages\\";
	
	private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg","image/jpg");
	
	@Override
	public boolean uploadProfileImage(String email, MultipartFile imageFile) {
		if(!contentTypes.contains(imageFile.getContentType())|| imageFile.getSize() >= 1024 * 1024 * 3) {
			//System.out.println("체크 통과 X");
			return false;
		} //파일 확장자 체크 + 3MB 이하만 가능
	
		Optional<User> ouser = userDao.findUserByEmail(email);
		
		if(!ouser.isPresent()) return false;
		else {
			
			String filePath = new String(baseUrl + email);
		
			try {
				imageFile.transferTo(new File(filePath));
			}catch (Exception e) {
				return false;
			}
			
			return true;
			
		}
	}

}
