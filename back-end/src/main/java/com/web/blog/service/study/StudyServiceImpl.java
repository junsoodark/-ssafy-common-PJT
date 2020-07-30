package com.web.blog.service.study;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.study.StudyDao;
import com.web.blog.model.address.Address;
import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;

@Service
@Transactional
public class StudyServiceImpl implements StudyService {
	@Autowired
	StudyDao studyDao;

	@Override
	public void create(final User user, final Address address, final String title, final String content, final LocalDate startDate, final LocalDate endDate) {
		Study study = new Study();
		study.setUser(user);
		study.setAddress(address);
		study.setTitle(title);
		study.setContent(content);
		study.setStartDate(startDate);
		study.setEndDate(endDate);
		studyDao.save(study);
	}

	@Override
	public boolean delete(final int studyId) {
		Study study = findStudyByStudyId(studyId);
		if (study != null) {
			studyDao.delete(study);
			return true;
		} return false;
	}
	
	@Override
	public boolean update(final Address address, final int studyId, final String title, final String content, final LocalDate startDate, final LocalDate endDate) {
		Optional<Study> studyOpt = studyDao.findStudyByStudyId(studyId);
		if(studyOpt.isPresent()==false) return false;
		studyOpt.ifPresent(study->{
			study.setAddress(address);
			study.setTitle(title);
			study.setContent(content);
			study.setStartDate(startDate);
			study.setEndDate(endDate);
			studyDao.save(study);
		}); return true;
	}
	
	@Override
	public Study findStudyByStudyId(final int studyId) {
		Optional<Study> studyOpt = studyDao.findStudyByStudyId(studyId);
		return studyOpt.isPresent() ? studyOpt.get() : null;
	}

	@Override
	public Map<String, Object> Study2SimpleInfo(Study study) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("studyId", study.getStudyId());
		ret.put("title", study.getTitle());
		// ret.put("img", study.getImage());
		return ret;
	}
	
	@Override
	public Map<String, Object> Study2DetailInfo(Study study) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("title", study.getTitle());
		ret.put("content", study.getContent());
		ret.put("mgrEmail", study.getUser().getEmail());
		ret.put("mgrName", study.getUser().getName());
		ret.put("numMembers", study.getMembers().size());
		ret.put("startDate", study.getStartDate());
		ret.put("endDate", study.getEndDate());
		ret.put("si", study.getAddress().getSi());
		ret.put("gu", study.getAddress().getGu());
		return ret;
	}

	@Override
	public List<Map<String, Object>> findAllStudiesSimpleInfo() {
		List<Map<String, Object>> ret = new ArrayList<>();
		for(Study study : studyDao.findAll())
			ret.add(Study2SimpleInfo(study));
		return ret;
	}

	@Override
	public Map<String, Object> findStudyDetailInfoByStudyId(final int studyId) {
		Study study = findStudyByStudyId(studyId);
		return study==null ? null : Study2DetailInfo(study);
	}
}
