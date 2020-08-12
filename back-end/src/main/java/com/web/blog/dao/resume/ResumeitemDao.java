package com.web.blog.dao.resume;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.resume.Resume;
import com.web.blog.model.resume.Resumeitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeitemDao extends JpaRepository<Resumeitem, String> {
    Optional<Resumeitem> findResumeitemById(int id);

    List<Resumeitem> findByResume(Resume resume);

    List<Resumeitem> findByTitleLike(String title);

}