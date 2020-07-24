package com.web.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.StudyDao;
import com.web.blog.model.study.Study;

@Service
public class StudyServiceImpl implements StudyService{
	
	@Autowired
	StudyDao studyDao;
	
	
	@Override
	public Study create(Study s) {
		// TODO Auto-generated method stub
		return studyDao.save(s);
	}

}
