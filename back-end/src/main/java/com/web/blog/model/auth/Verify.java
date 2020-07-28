package com.web.blog.model.auth;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Verify {
	@Id
	private String email;
	private String code;

	public Verify() {

	}

	public Verify(String email, String code) {
		this.email = email;
		this.code = code;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
