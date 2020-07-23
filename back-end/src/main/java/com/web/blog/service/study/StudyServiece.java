package com.web.blog.service.study;

import java.util.List;

import com.web.blog.model.study.Study;

import org.springframework.stereotype.Service;

@Service
public interface StudyServiece {
    public void update(Study study);

    public void delete(int study_id);

    public void create(Study study);

    public Study search(int study_id);

    public Study search(String title);

    public List<Study> searchAll();
}