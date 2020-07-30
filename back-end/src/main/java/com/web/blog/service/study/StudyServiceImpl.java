package com.web.blog.service.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.study.StudyDao;
import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;

@Service
@Transactional
public class StudyServiceImpl implements StudyService {
	@Autowired
	StudyDao studyDao;

	@Override
	public Study findStudyByStudyId(final int studyId) {
		Optional<Study> studyOpt = studyDao.findStudyByStudyId(studyId);
		return studyOpt.isPresent() ? studyOpt.get() : null;
	}

	@Override
	public Map<String, Object> Study2Map(final Study study) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("studyId", study.getStudyId());
		ret.put("title", study.getTitle());
		ret.put("content", study.getContent());
		ret.put("si", study.getAddress().getSi());
		ret.put("gu", study.getAddress().getGu());
		ret.put("startDate", study.getStartDate());
		ret.put("endDate", study.getEndDate());
		ret.put("mgrEmail", study.getUser().getEmail());
		ret.put("mgrName", study.getUser().getName());
		return ret;
	}

	@Override
	public List<Map<String, Object>> findAllStudies() {
		List<Map<String, Object>> ret = new ArrayList<>();
		for (Study study : studyDao.findAll())
			ret.add(Study2Map(study));
		return ret;
	}

	@Override
	public void create(Study study) {
		studyDao.save(study);
	}

	@Override
	public Study delete(final int studyId) {
		Study study = findStudyByStudyId(studyId);
		if (study != null) {
			studyDao.delete(study);
		}
		return study;

	}

	@Override
	public boolean join(final int studyId, User user) {
		Optional<Study> studyOpt = studyDao.findStudyByStudyId(studyId);
		if(studyOpt.isPresent()) {
			studyOpt.get().addMember(user);
			return true;
		}
		else return false;
	}

	@Override
	public List<Map<String, Object>> findMembers(final int studyId) {
		List<Map<String, Object>> ret = null;
		Optional<Study> studyOpt = studyDao.findStudyByStudyId(studyId);
		if(studyOpt.isPresent()) ret = studyOpt.get().getMemberList();
		return ret;
	}
}
