package com.web.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.blog.model.study.Study;

public interface StudyDao extends JpaRepository<Study, Integer>{
	
	Study save(Study s);
	
	
}
