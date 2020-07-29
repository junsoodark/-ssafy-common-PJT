package com.web.blog.model.study;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.web.blog.model.user.User;

import lombok.Data;
@Data
@Entity
public class StudyMember {
	@Id @GeneratedValue
	@Column(name= "id")
	private Long Id;
	
	
	@ManyToOne
	@JoinColumn(name="study_id")
	private Study study;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
}
