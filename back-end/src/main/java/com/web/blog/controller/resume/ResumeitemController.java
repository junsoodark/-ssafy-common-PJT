package com.web.blog.controller.resume;

import java.util.List;
import java.util.Map;

import com.web.blog.model.resume.Resumeitem;
import com.web.blog.service.resume.ResumeService;
import com.web.blog.service.resume.ResumeitemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class ResumeitemController {
    @Autowired
    ResumeitemService resumeitemService;

    @GetMapping("/resumeitem/all")
    @ApiOperation(value = "")
    public ResponseEntity<Object> findAll() {
        List<Map<String, Object>> res = resumeitemService.findAllResumeitem();
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @GetMapping("resumeitem/{resumeitemId}")
    @ApiOperation(value = "")
    public ResponseEntity<Object> read(@PathVariable int resumeitemId) {
        Map<String, Object> res = resumeitemService.Resumeitem2Map(resumeitemService.read(resumeitemId));
        if (res == null) {
            return new ResponseEntity<Object>("존재하지 않는 항목입니다.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @GetMapping("/resumeitem/resume/{resumeId}")
    @ApiOperation(value = "")
    public ResponseEntity<Object> readByResume(@PathVariable int resumeId) {
        List<Resumeitem> list = resumeitemService.readByResume(resumeId);
        return new ResponseEntity<Object>(list, HttpStatus.OK);
    }
}