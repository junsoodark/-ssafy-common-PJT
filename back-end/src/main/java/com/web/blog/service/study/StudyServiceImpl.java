package com.web.blog.service.study;

import java.util.List;

import com.web.blog.dao.study.StudyDao;
import com.web.blog.model.study.Study;

import org.springframework.beans.factory.annotation.Autowired;

public class StudyServiceImpl implements StudyServiece {

    @Autowired
    StudyDao studyDao;

    @Override
    public void update(Study study) {
        studyDao.save(study);
    }

    @Override
    public void delete(Study study) {
        studyDao.delete(study);
    }

    @Override
    public void create(Study study) {
        studyDao.save(study);
    }

    @Override
    public Study search(int study_id) {
        return studyDao.getStudyById(study_id);
    }

    @Override
    public Study search(String title) {
        return studyDao.getStudyByTitle(title);
    }

    @Override
    public List<Study> searchAll() {
        return studyDao.findAll();
    }

}