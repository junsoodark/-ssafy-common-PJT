package com.web.blog.controller.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.study.Study;
import com.web.blog.service.study.StudyService;

import io.swagger.annotations.ApiOperation;

@RestController
public class StudyController {
//	@Autowired
//	StudyService studyService;
//	
//	@GetMapping
//	@ApiOperation(value="테스트를 위한 임시 api 입니다.")
//	public ResponseEntity test(@RequestParam final int study_id) {
//		Study study = studyService.findStudyByStudyId(study_id);
//		if(study==null) return new ResponseEntity(null, HttpStatus.FORBIDDEN);
//		return new ResponseEntity(study, HttpStatus.OK);
//	}
}