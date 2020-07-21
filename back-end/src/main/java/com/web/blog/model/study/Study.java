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
}
