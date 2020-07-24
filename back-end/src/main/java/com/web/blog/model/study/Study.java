package com.web.blog.model.study;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Study {
	private int study_id;
	private int mgr_id;
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
