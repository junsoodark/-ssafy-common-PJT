package com.web.blog.service.study;

import java.util.List;
import java.util.Map;

import com.web.blog.model.study.Study;

public interface StudyService {
	public Study findStudyByStudyId(final int studyId);
	public Map<String, Object> Study2Map(final Study study);
	public List<Map<String, Object>> findAllStudies();
	public void create(final Study study);
	public Study delete(final int studyId);
}
