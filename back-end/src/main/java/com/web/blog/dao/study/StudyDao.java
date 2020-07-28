package com.web.blog.dao.study;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.blog.model.study.Study;

public interface StudyDao extends JpaRepository<Study, String> {
	Optional<Study> findStudyByStudyId(final int studyId);
	Study findStudyByTitle(String title);
}