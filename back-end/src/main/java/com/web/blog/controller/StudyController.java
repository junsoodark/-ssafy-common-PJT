package com.web.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.study.Study;
import com.web.blog.service.StudyService;


@CrossOrigin("{*}")
@RestController
@RequestMapping("/study")
public class StudyController {

	
	@Autowired
	StudyService studyService;
	
	@PostMapping("/")
	ResponseEntity<T> create(@RequestBody @Validated Study study){
		return	studyService.create(study);
		
	}
	
}
