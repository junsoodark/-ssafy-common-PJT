package com.web.blog.controller.resume;

import java.util.List;
import java.util.Map;

import com.web.blog.model.resume.Resume;
import com.web.blog.model.user.User;
import com.web.blog.service.resume.ResumeService;
import com.web.blog.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class ResumeController {
    @Autowired
    ResumeService resumeService;

    @Autowired
    UserService userService;

    @GetMapping("/resume/all")
    @ApiOperation(value = "")
    public ResponseEntity<Object> findAll() {
        List<Map<String, Object>> res = resumeService.findAllResume();
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @GetMapping("/resume/{resumeId}")
    @ApiOperation(value = "")
    public ResponseEntity<Object> read(@PathVariable int resumeId) {
        Map<String, Object> res = resumeService.Resume2Map(resumeService.read(resumeId));
        if (res == null) {
            return new ResponseEntity<Object>("존재하지 않는 자소서입니다.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @PostMapping("/resume")
    @ApiOperation(value = "")
    public ResponseEntity<Object> create(   @RequestParam String title,
                                            @RequestParam String email,
                                            @RequestParam String company, 
                                            @RequestParam String job){
        User user = userService.findUserByEmail(email);
        if(user == null){
            return new ResponseEntity<Object>("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
        }
        Resume resume = resumeService.create(user, title, company, job);
        if(resume == null){
            return new ResponseEntity<Object>("자소서를 생성할 수 없습니다.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Object>("자소서 생성에 성공했습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/resume")
    @ApiOperation(value = "")
    public ResponseEntity<Object> delete(int resumeId){
        if(!resumeService.delete(resumeId)){
            return new ResponseEntity<Object>("삭제에 실패했습니다.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Object>("삭제에 성공했습니다.", HttpStatus.OK);
    }

    @PutMapping("/resume")
    @ApiOperation(value = "")
    public ResponseEntity<Object> update(   @RequestParam int resumeId,
                                            @RequestParam String title,
                                            @RequestParam String company, 
                                            @RequestParam String job){
        Resume resume = resumeService.read(resumeId);
        resume.setCompany(company);
        resume.setJob(job);
        resume.setTitle(title);
        if(!resumeService.update(resume)){
            return new ResponseEntity<Object>("업데이트에 실패하였습니다.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Object>("업데이트에 성공하였습니다.", HttpStatus.OK);
    }
}