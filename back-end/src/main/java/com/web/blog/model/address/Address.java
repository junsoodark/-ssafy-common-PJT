package com.web.blog.model.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Address {
	@Id @GeneratedValue
	private String id;
	
	private String si;
	private String gu;
}
