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
import com.web.blog.service.study.StudyApprovalService;
import com.web.blog.service.study.StudyMemberService;
import com.web.blog.service.study.StudyService;
import com.web.blog.service.user.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class StudyMemberController {
	@Autowired
	StudyMemberService studyMemberService;
	
	@Autowired
	StudyApprovalService studyApprovalService;
	
	@Autowired
	StudyService studyService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/study/member/apply")
	@ApiOperation(value="사이트 회원이 스터디에 가입신청을 합니다.")
	public ResponseEntity apply(@RequestParam final int studyId, @RequestParam final String email) {
		User user = userService.findUserByEmail(email);
		if(user==null) return new ResponseEntity("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		
		Study study = studyService.findStudyByStudyId(studyId);
		if(study==null) return new ResponseEntity("존재하지 않는 스터디입니다.", HttpStatus.NOT_FOUND);
			
		if(studyMemberService.isExistMember(study, user)) return new ResponseEntity("이미 가입된 멤버입니다.", HttpStatus.CONFLICT);
		
		if(studyMemberService.isFull(study)) return new ResponseEntity("스터디 정원이 가득찼습니다.", HttpStatus.CONFLICT);
		if(studyApprovalService.apply(study, user)==false) return new ResponseEntity("스터디에 가입신청을할 수 없습니다. 관리자에게 문의바랍니다.", HttpStatus.FORBIDDEN);
		return new ResponseEntity("스터디 가입을 승인했습니다.", HttpStatus.OK);
		
	}
	
	@DeleteMapping("/study/member/disapply")
	@ApiOperation(value="사이트 회원이 스터디 가입신청을 취소합니다.")
	public ResponseEntity disapply(@RequestParam final int studyId, @RequestParam final String email) {
		User user = userService.findUserByEmail(email);
		if(user==null) return new ResponseEntity("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		
		Study study = studyService.findStudyByStudyId(studyId);
		if(study==null) return new ResponseEntity("존재하지 않는 스터디입니다.", HttpStatus.NOT_FOUND);
		
		if(studyMemberService.isExistMember(study, user)) return new ResponseEntity("이미 가입된 멤버입니다.", HttpStatus.CONFLICT);
				
		if(studyApprovalService.disapply(study, user)==false) return new ResponseEntity("스터디에 거절 에러 발생. 관리자에게 문의바랍니다.", HttpStatus.FORBIDDEN);
		return new ResponseEntity("스터디 가입을 거절했습니다.", HttpStatus.OK);
	}
	
	
	@PostMapping("/study/member/approve")
	@ApiOperation(value="관지라자가 해당 스터디에 사용자를 가입시킵니다.")
	public ResponseEntity approve(@RequestParam final int studyId, @RequestParam final String email) {
		User user = userService.findUserByEmail(email);
		if(user==null) return new ResponseEntity("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		
		Study study = studyService.findStudyByStudyId(studyId);
		if(study==null) return new ResponseEntity("존재하지 않는 스터디입니다.", HttpStatus.NOT_FOUND);
		
		if(studyMemberService.isExistMember(study, user)) return new ResponseEntity("이미 가입된 멤버입니다.", HttpStatus.CONFLICT);
	
		if(studyMemberService.isFull(study)) return new ResponseEntity("스터디 멤버 수가 초과하여 가입할 수 없습니다.", HttpStatus.CONFLICT);
		if(!studyApprovalService.in(study,user)) return new ResponseEntity("가입 신청 목록에 없는 사용자입니다.",HttpStatus.CONFLICT);
			
		if(studyMemberService.approve(study, user)==false) return new ResponseEntity("스터디에 가입승인에 실패했습니다. 관리자에게 문의바랍니다.", HttpStatus.FORBIDDEN);
		
		return new ResponseEntity("스터디 가입승인이 성공되었습니다.", HttpStatus.OK);
	}
	
	@DeleteMapping("/study/member/disapprove")
	@ApiOperation(value="관지라자가 해당 스터디에 사용자를 가입을 거절합니다.")
	public ResponseEntity disapprove(@RequestParam final int studyId, @RequestParam final String email) {
		User user = userService.findUserByEmail(email);
		if(user==null) return new ResponseEntity("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		
		Study study = studyService.findStudyByStudyId(studyId);
		if(study==null) return new ResponseEntity("존재하지 않는 스터디입니다.", HttpStatus.NOT_FOUND);
		
		if(studyMemberService.isExistMember(study, user)) return new ResponseEntity("이미 가입된 멤버입니다.", HttpStatus.CONFLICT);
	
		//if(studyMemberService.isFull(study)) return new ResponseEntity("스터디 멤버 수가 초과하여 가입할 수 없습니다.", HttpStatus.CONFLICT);
		if(!studyApprovalService.in(study,user)) return new ResponseEntity("가입 신청 목록에 없는 사용자입니다.",HttpStatus.CONFLICT);
		
		if(studyMemberService.disapprove(study, user)==false) return new ResponseEntity("스터디 가입승인 거절에 실패했습니다. 관리자에게 문의바랍니다.", HttpStatus.FORBIDDEN);
		
		return new ResponseEntity("스터디 가입승인을 거절했습니다.", HttpStatus.OK);
	}
	
	@DeleteMapping("/study/member")
	@ApiOperation(value="스터디 아이디와 사용자 이메일을 입력받아 가입 여부를 확인하고, 해당 스터디에서 사용자를 탈퇴시킵니다.")
	public ResponseEntity leave(@RequestParam final int studyId, @RequestParam final String email) {
		User user = userService.findUserByEmail(email);
		if(user==null) return new ResponseEntity("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		
		Study study = studyService.findStudyByStudyId(studyId);
		if(study==null) return new ResponseEntity("존재하지 않는 스터디입니다.", HttpStatus.NOT_FOUND);
	
		if(studyMemberService.isExistMember(study, user)==false) return new ResponseEntity("스터디의 멤버가 아닙니다.", HttpStatus.NOT_FOUND);
		
		if(studyMemberService.leave(study, user)==false) return new ResponseEntity("스터디에서 탈퇴할 수 없습니다. 관리자에게 문의바랍니다.", HttpStatus.FORBIDDEN);
		return new ResponseEntity("스터디에서 탈퇴되었습니다.", HttpStatus.OK);
	}
	
	@GetMapping("/study/{study_id}/list")
	@ApiOperation(value =("스터디 id 를 입력받고, 해당 스터디에 가입된 스터디원 목록을 반환합니다."))
	public ResponseEntity getStudyUserList(@PathVariable int study_id) {
		Study study = studyService.findStudyByStudyId(study_id);
		if(study==null) return new ResponseEntity("존재하지 않는 스터디입니다.", HttpStatus.NOT_FOUND);
		
		List< Map<String,String>> res = studyMemberService.getStudyUserList(study);
		
		return new ResponseEntity(res,HttpStatus.OK);
	}
	
	@GetMapping("/study/{study_id}/approvelist")
	@ApiOperation(value =("스터디 id 를 입력받고, 해당 스터디에 가입된 스터디원 목록을 반환합니다."))
	public ResponseEntity getStudyApprovalUserList(@PathVariable int study_id) {
		Study study = studyService.findStudyByStudyId(study_id);
		if(study==null) return new ResponseEntity("존재하지 않는 스터디입니다.", HttpStatus.NOT_FOUND);
		
		List< Map<String,String>> res = studyMemberService.getStudyApprovalUserList(study);
		System.out.println(res.size());
		return new ResponseEntity(res,HttpStatus.OK);
	}
	
	
	@GetMapping("/study/email")
	@ApiOperation(value="사용자의 이메일을 입력받아, 사용자가 가입한 모든 스터디의 목록을 반환합니다.")
	public ResponseEntity findStudiesByEmail(@RequestParam final String email) {
		User user = userService.findUserByEmail(email);
		if(user==null) return new ResponseEntity("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		
		List<Map<String, Object>> res = new ArrayList<>();
		for(Study study : user.getStudies())
			res.add(studyService.Study2Map(study));
		return new ResponseEntity(res, HttpStatus.OK);
	}
}
