package com.web.blog.service.study;

import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;

public interface StudyMemberService {
	public boolean approve(Study study, User user);
	public boolean disapprove(Study study, User user);
	
	public boolean leave(Study study, User user);
	public boolean isFull(final Study study);
	public boolean isExistMember(final Study study, final User user);
}
