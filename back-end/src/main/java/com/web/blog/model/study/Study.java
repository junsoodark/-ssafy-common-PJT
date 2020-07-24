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
	
	@ManyToOne(targetEntity = Address.class)
	private int address_id;
	
	private String title;
	private String content;
	private Date startDate;
	private Date endDate;
	private String si;
	private String gu;

	public int getStudy_id() {
		return this.study_id;
	}

	public void setStudy_id(int study_id) {
		this.study_id = study_id;
	}

	public int getMgr_id() {
		return this.mgr_id;
	}

	public void setMgr_id(int mgr_id) {
		this.mgr_id = mgr_id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
