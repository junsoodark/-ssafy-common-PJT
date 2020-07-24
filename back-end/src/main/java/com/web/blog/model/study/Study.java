package com.web.blog.model.study;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;
import com.web.blog.model.address.Address;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Study {
	@Id @GeneratedValue
	private int study_id;
	
	@ManyToOne(targetEntity = User.class)
	private int mgr_id;
	
	@ManyToMany
	@JoinTable(name = "user")
	private Set <User> users;
	
	private String title;
	private String content;
	private Date startDate;
	private Date endDate;
	
	@ManyToOne(targetEntity = Address.class)
	private int address_id;
	
}
