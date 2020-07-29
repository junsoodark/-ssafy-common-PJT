package com.web.blog.controller.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.web.blog.model.address.Address;
import com.web.blog.model.study.Study;
import com.web.blog.model.user.User;
import com.web.blog.service.address.AddressService;
import com.web.blog.service.study.StudyService;
import com.web.blog.service.user.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class StudyController {
	@Autowired
	StudyService studyService;

	@Autowired
	UserService userService;

	@Autowired
	AddressService addressService;

	@GetMapping("/study/all")
	@ApiOperation(value = "모든 스터디의 리스트를 반환합니다.")
	public ResponseEntity findAll() {
		List<Map<String, Object>> res = studyService.findAllStudies();
		return new ResponseEntity(res, HttpStatus.OK);
	}

	@GetMapping("/study/{studyId}")
	@ApiOperation(value = "스터디 아이디를 입력받아 일치하는 스터디의 정보를 반환합니다.")
	public ResponseEntity read(@PathVariable final int studyId) {
		Study res = studyService.findStudyByStudyId(studyId);
		if (res == null)
			return new ResponseEntity("존재하지 않는 스터디입니다.", HttpStatus.NOT_FOUND);
		return new ResponseEntity(res, HttpStatus.OK);
	}

	@PostMapping("/study")
	@ApiOperation(value = "")
	public ResponseEntity create(@RequestParam final String email, @RequestParam final String title,
			@RequestParam final String content, @DateTimeFormat(iso = ISO.DATE) @RequestParam final LocalDate startDate,
			@DateTimeFormat(iso = ISO.DATE) @RequestParam final LocalDate endDate, @RequestParam final String si,
			@RequestParam final String gu) {
		final User user = userService.findUserByEmail(email);
		if (user == null)
			return new ResponseEntity("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);

		final Address address = addressService.findAddressBySiAndGu(si, gu);
		if (address == null)
			return new ResponseEntity("존재하지 않는 주소입니다.", HttpStatus.NOT_FOUND);

		if (endDate.compareTo(startDate) < 0)
			return new ResponseEntity("종료일은 시작일 보다 빠를수 없습니다", HttpStatus.FORBIDDEN);

		Study study = new Study();
		study.setUser(user);
		study.setAddress(address);
		study.setTitle(title);
		study.setContent(content);
		study.setStartDate(startDate);
		study.setEndDate(endDate);
		studyService.create(study);

		return new ResponseEntity("스터디 생성에 성공했습니다.", HttpStatus.OK);
	}

	@DeleteMapping("/study/{studyId}")
	@ApiOperation(value = "")
	public ResponseEntity delete(@PathVariable final int studyId) {
		Study study = studyService.delete(studyId);
		if (study == null) {
			return new ResponseEntity("해당 스터디가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity("스터디 삭제에 성공했습니다.", HttpStatus.OK);
	}

	@PutMapping("/study")
	@ApiOperation(value = "")
	public ResponseEntity update(@RequestParam final int studyId, @RequestParam final String email,
			@RequestParam final String title, @RequestParam final String content,
			@DateTimeFormat(iso = ISO.DATE) @RequestParam final LocalDate startDate,
			@DateTimeFormat(iso = ISO.DATE) @RequestParam final LocalDate endDate, @RequestParam final String si,
			@RequestParam final String gu) {
		Study study = studyService.findStudyByStudyId(studyId);
		final Address address = addressService.findAddressBySiAndGu(si, gu);
		final User user = userService.findUserByEmail(email);
		if (address == null)
			return new ResponseEntity("존재하지 않는 주소입니다.", HttpStatus.NOT_FOUND);

		if (endDate.compareTo(startDate) < 0)
			return new ResponseEntity("종료일은 시작일 보다 빠를수 없습니다", HttpStatus.FORBIDDEN);
		study.setUser(user);
		study.setAddress(address);
		study.setTitle(title);
		study.setContent(content);
		study.setStartDate(startDate);
		study.setEndDate(endDate);
		studyService.create(study);
		return new ResponseEntity("스터디 수정에 성공했습니다.", HttpStatus.OK);
	}
}