package com.web.blog.controller.study;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;
import com.web.blog.service.study.StudyMemberService;
import com.web.blog.service.study.StudyService;
import com.web.blog.service.user.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class StudyMemberController {
	@Autowired
	StudyMemberService studyMemberService;
	
	@Autowired
	StudyService studyService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/study/member/join")
	@ApiOperation(value="스터디 아이디와 사용자 이메일을 입력받아 가입 여부를 확인하고, 해당 스터디에 사용자를 가입시킵니다.")
	public ResponseEntity join(@RequestParam final int studyId, @RequestParam final String email) {
		User user = userService.findUserByEmail(email);
		if(user==null) return new ResponseEntity("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		
		Study study = studyService.findStudyByStudyId(studyId);
		if(study==null) return new ResponseEntity("존재하지 않는 스터디입니다.", HttpStatus.NOT_FOUND);
	
		if(studyMemberService.join(study, user)==false) return new ResponseEntity("이미 가입된 멤버입니다.", HttpStatus.CONFLICT);
		return new ResponseEntity("스터디에 가입되었습니다.", HttpStatus.OK);
	}
	
	@DeleteMapping("/study/member")
	@ApiOperation(value="스터디 아이디와 사용자 이메일을 입력받아 가입 여부를 확인하고, 해당 스터디에서 사용자를 탈퇴시킵니다.")
	public ResponseEntity leave(@RequestParam final int studyId, @RequestParam final String email) {
		User user = userService.findUserByEmail(email);
		if(user==null) return new ResponseEntity("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		
		Study study = studyService.findStudyByStudyId(studyId);
		if(study==null) return new ResponseEntity("존재하지 않는 스터디입니다.", HttpStatus.NOT_FOUND);
	
		if(studyMemberService.leave(study, user)==false) return new ResponseEntity("스터디의 멤버가 아닙니다.", HttpStatus.FORBIDDEN);
		return new ResponseEntity("스터디에서 탈퇴되었습니다.", HttpStatus.OK);
	}
	
	@GetMapping("/study/email")
	@ApiOperation(value="사용자의 이메일을 입력받아, 사용자가 가입한 모든 스터디의 목록을 반환합니다.")
	public ResponseEntity findStudiesByEmail(@RequestParam final String email) {
		User user = userService.findUserByEmail(email);
		if(user==null) return new ResponseEntity("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		
		List<Map<String, Object>> res = new ArrayList<>();
		for(Study study : user.getStudies())
			res.add(studyService.Study2SimpleInfo(study));
		return new ResponseEntity(res, HttpStatus.OK);
	}
}
