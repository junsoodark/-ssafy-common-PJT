package com.web.blog.service.study;

import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;

public interface StudyMemberService {
	public boolean join(final Study study, final User user);
	public boolean leave(final Study study, final User user);
}
