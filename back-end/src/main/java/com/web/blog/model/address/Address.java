package com.web.blog.model.address;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Address")
public class Address {
	
	private String si;
	private String gu;
}
