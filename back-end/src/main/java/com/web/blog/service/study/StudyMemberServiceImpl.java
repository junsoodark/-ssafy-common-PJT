package com.web.blog.service.study;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;

@Service
@Transactional
public class StudyMemberServiceImpl implements StudyMemberService {
	@Override
	public boolean join(Study study, User user) {
		return study.addMember(user);
	}

	@Override
	public boolean leave(Study study, User user) {
		return study.removeMember(user);
	}
}