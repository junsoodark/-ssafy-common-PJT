package com.web.blog.service.study;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.web.blog.model.address.Address;
import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;

public interface StudyService {
	public void create(final User user, final Address address, final String title, final String content, final LocalDate startDate, final LocalDate endDate);
	public boolean delete(final int studyId);
	public boolean update(final Address address, final int studyId, final String title, final String content, final LocalDate startDate, final LocalDate endDate);

	public Study findStudyByStudyId(final int studyId);
	public Map<String, Object> Study2SimpleInfo(final Study study);
	public Map<String, Object> Study2DetailInfo(final Study study);

	public List<Map<String, Object>> findAllStudiesSimpleInfo();
	public Map<String, Object> findStudyDetailInfoByStudyId(final int studyId);
}
