package com.web.blog.controller.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import com.web.blog.dao.study.StudyDao;
import com.web.blog.model.study.Study;
import com.web.blog.service.study.StudyService;

import io.swagger.annotations.ApiOperation;

@RestController
public class StudyController {

    @Autowired
    StudyDao studyDao;
    //
    // @GetMapping
    // @ApiOperation(value="테스트를 위한 임시 api 입니다.")
    // public ResponseEntity test(@RequestParam final int study_id) {
    // Study study = studyService.findStudyByStudyId(study_id);
    // if(study==null) return new ResponseEntity(null, HttpStatus.FORBIDDEN);
    // return new ResponseEntity(study, HttpStatus.OK);
    // }

    @PostMapping("/study")
    @ApiOperation(value = "스터디 정보를 입력받아 생성")
    public void create(Study study) {
        studyDao.save(study);
    }

    @GetMapping("/study/{study_id}")
    @ApiOperation(value = "study_id로 study를 반환하는 api 입니다.")
    public Study read(int study_id) {
        Optional<Study> study = studyDao.findStudyByStudyId(study_id);
        return study.get();
    }

    @PutMapping("/study")
    @ApiOperation(value = "스터디 정보를 입력받아 기존의 스터디 수정")
    public void update(Study study) {
        studyDao.save(study);
    }

    @DeleteMapping("/study")
    @ApiOperation(value = "스터디 아이디를 입력받아 스터디 삭제")
    public void delete(int study_id) {
        studyDao.delete(studyDao.findStudyByStudyId(study_id).get());
    }
}