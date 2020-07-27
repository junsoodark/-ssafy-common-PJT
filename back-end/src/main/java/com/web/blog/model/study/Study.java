package com.web.blog.model.study;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name = "mgr_id")
	private int mgrId;
	
	@ManyToOne(targetEntity = Address.class)
	@JoinColumn(name = "address_id")
	private int addressId;
	
	private String title;
	private String content;
	private Date startDate;
	private Date endDate;
	
	/*
	 * @OneToMany(mappedBy="study") private List<StudyUser> studyUsers = new
	 * ArrayList<>();
	 */

	public int getStudyId() {
		return studyId;
	}

	public void setStudyId(int studyId) {
		this.studyId = studyId;
	}

	public int getMgrId() {
		return mgrId;
	}

	public void setMgrId(int mgrId) {
		this.mgrId = mgrId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/*
	 * public List<StudyUser> getStudyUsers() { return studyUsers; }
	 * 
	 * public void setStudyUsers(List<StudyUser> studyUsers) { this.studyUsers =
	 * studyUsers; }
	 */

	
	
	
	
	
	
	
}