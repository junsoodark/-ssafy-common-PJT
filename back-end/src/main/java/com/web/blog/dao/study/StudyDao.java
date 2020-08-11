package com.web.blog.dao.study;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;

@Repository
@Transactional
public interface StudyDao extends StudyApprovalDao,StudyMemberDao, JpaRepository<Study, String> {
	Optional<Study> findStudyByStudyId(final int studyId);

	

	
	
//	Study findStudyByTitle(String title);
}