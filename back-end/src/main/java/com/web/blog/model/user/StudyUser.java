package com.web.blog.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.web.blog.model.study.Study;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StudyUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "study_id")
	private Study study;
	
}
