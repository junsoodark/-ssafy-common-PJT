package com.web.blog.service.resume;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.web.blog.dao.resume.ResumeDao;
import com.web.blog.dao.resume.ResumeitemDao;
import com.web.blog.model.resume.Resume;
import com.web.blog.model.resume.Resumeitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResumeitemServiceImpl implements ResumeitemService {
    @Autowired
    private ResumeitemDao resumeitemDao;
    @Autowired
    private ResumeDao resumeDao;

    @Override
    public Resumeitem create(Resume resume, String title, String content) {
        Resumeitem resumeitem = new Resumeitem();
        resumeitem.setResume(resume);
        resumeitem.setContent(content);
        resumeitem.setTitle(title);
        return resumeitemDao.save(resumeitem);
    }

    @Override
    public Resumeitem read(int id) {
        Optional<Resumeitem> resumeitemOpt = resumeitemDao.findResumeitemById(id);
        return resumeitemOpt.isPresent() ? resumeitemOpt.get() : null;
    }

    @Override
    public List<Resumeitem> readByResume(int resumeId) {
        Optional<Resume> resumeOpt = resumeDao.findResumeById(resumeId);
        if (!resumeOpt.isPresent()) {
            return null;
        }
        Resume resume = resumeOpt.get();
        List<Resumeitem> list = resumeitemDao.findByResume(resume);
        return list;
    }

    @Override
    public boolean update(Resumeitem resumeitem) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Map<String, Object>> findAllResumeitem() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> Resumeitem2Map(Resumeitem resumeitem) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean create(Resumeitem resumeitem) {
        if (resumeitemDao.findResumeitemById(resumeitem.getId()).isPresent()) {
            return false;
        }
        resumeitemDao.save(resumeitem);
        return true;
    }

}