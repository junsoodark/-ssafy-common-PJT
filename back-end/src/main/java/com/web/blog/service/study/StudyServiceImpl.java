//package com.web.blog.service.study;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.web.blog.dao.study.StudyDao;
//import com.web.blog.model.study.Study;
//
//@Service
//public class StudyServiceImpl implements StudyService{
//	@Autowired
//	StudyDao studyDao;
//
//	@Override
//	public Study findStudyByStudyId(int study_id) {
//		Optional<Study> studyOpt = studyDao.findStudyByStudyId(study_id);
//		return studyOpt.isPresent() ? studyOpt.get() : null;
////		return null;
//	}
//}
