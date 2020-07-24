package com.web.blog.model.study;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	@GeneratedValue
	private int study_id;
	
	@ManyToOne(targetEntity = User.class)
	private int mgr_id;
	
	@ManyToOne(targetEntity = Address.class)
	private int address_id;
	
	private String title;
	private String content;
	private Date startDate;
	private Date endDate;
	private String si;
	private String gu;
}
