package com.web.blog.model.study;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.web.blog.model.address.Address;
import com.web.blog.model.user.User;

@Entity
public class Study {

	@Id
	@GeneratedValue
	@Column(name = "study_id")
	private int studyId;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "mgr_id")
	private int mgrId;

	@ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private int addressId;

	private String title;
	private String content;
	private Date startDate;
	private Date endDate;

	public Study() {
	}

	public Study(int studyId, int mgrId, int addressId, String title, String content, Date startDate, Date endDate) {
		this.studyId = studyId;
		this.mgrId = mgrId;
		this.addressId = addressId;
		this.title = title;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getStudyId() {
		return this.studyId;
	}

	public void setStudyId(int studyId) {
		this.studyId = studyId;
	}

	public int getMgrId() {
		return this.mgrId;
	}

	public void setMgrId(int mgrId) {
		this.mgrId = mgrId;
	}

	public int getAddressId() {
		return this.addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
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

	/*
	 * @OneToMany(mappedBy="study") private List<StudyUser> studyUsers = new
	 * ArrayList<>();
	 */

	/*
	 * public List<StudyUser> getStudyUsers() { return studyUsers; }
	 * 
	 * public void setStudyUsers(List<StudyUser> studyUsers) { this.studyUsers =
	 * studyUsers; }
	 */

}