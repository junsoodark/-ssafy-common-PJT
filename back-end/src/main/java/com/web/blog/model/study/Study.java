package com.web.blog.model.study;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.web.blog.model.address.Address;
import com.web.blog.model.user.StudyUser;
import com.web.blog.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Study {
	
	@Id
	@GeneratedValue
	@Column(name = "study_id")
	private int studyId;
	
	@ManyToOne(targetEntity = User.class)
	@Column(name = "mgr_id")
	private int mgrId;
	
	@ManyToOne(targetEntity = Address.class)
	@Column(name = "address_id")
	private int addressId;
	
	private String title;
	private String content;
	private Date startDate;
	private Date endDate;
	
	@OneToMany(mappedBy="study")
	private List<StudyUser> studyUsers = new ArrayList<>();
	
	
	
	
	
	
}