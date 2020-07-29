package com.web.blog.model.study;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.blog.model.address.Address;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "study_id")
	private int studyId;

	@JsonIgnore
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "mgr_id")
	private User user;

	@JsonIgnore
	@ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;
	
	private String title;
	private String content;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@OneToMany(mappedBy="study")
	private List <StudyMember> studies = new ArrayList<StudyMember>();
}