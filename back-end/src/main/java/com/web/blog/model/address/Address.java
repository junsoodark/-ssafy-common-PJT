package com.web.blog.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Address {
	@Id
	@GeneratedValue
	@Column(name = "address_id")
	private String addressId;

	private String si;
	private String gu;

	public Address() {
	}

	public Address(String addressId, String si, String gu) {
		this.addressId = addressId;
		this.si = si;
		this.gu = gu;
	}

	public String getSi() {
		return this.si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getGu() {
		return this.gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

}
