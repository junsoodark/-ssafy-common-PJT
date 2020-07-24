package com.web.blog.dao.study;

import java.util.List;

import com.web.blog.model.study.Study;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyDao extends JpaRepository<Study, String> {
    Study getStudyById(int id);

    Study getStudyByTitle(String title);

    void delete(Study study);

    Study save(Study study);

    List<Study>  findAll();

}