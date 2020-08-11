package com.web.blog.service.study;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.blog.dao.study.StudyDao;
import com.web.blog.dao.study.StudyMemberDao;
import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;

@Service
@Transactional
public class StudyMemberServiceImpl implements StudyMemberService {
	@Autowired
	private StudyDao studyDao;
	
	
	
	@Override
	public boolean approve(Study study, User user) {
		return studyDao.approve(study, user);
	}
	
	@Override
	public boolean disapprove(Study study, User user) {

		return studyDao.disapprove(study, user);
	}
	
	@Override
	public boolean leave(Study study, User user) {
		return studyDao.removeMember(study, user);
	}

	@Override
	public boolean isFull(Study study) {
		return studyDao.isFull(study);
	}

	@Override
	public boolean isExistMember(Study study, User user) {
		return studyDao.isExistMember(study, user);
	}
}
