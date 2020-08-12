package com.web.blog.service.resume;

import java.util.List;
import java.util.Map;

import com.web.blog.model.resume.Resume;
import com.web.blog.model.resume.Resumeitem;

public interface ResumeitemService {
    public Resumeitem create(Resume resume, String title, String content);

    public boolean create(Resumeitem resumeitem);

    public Resumeitem read(int id);

    public List<Resumeitem> readByResume(int resumeId);

    public boolean update(Resumeitem resumeitem);

    public boolean delete(int id);

    public List<Map<String, Object>> findAllResumeitem();

    public Map<String, Object> Resumeitem2Map(Resumeitem resumeitem);

}