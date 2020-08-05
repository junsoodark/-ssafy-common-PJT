package com.web.blog.dao.study;

import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;

public interface StudyMemberDao {
	public boolean addMember(Study study, User member);
	public boolean removeMember(Study study, User member);
	public boolean isFull(Study study);
	public boolean isExistMember(Study study, User member);
}
