package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.demo.modal.Student;
import com.example.demo.service.StudentServie;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	private StudentServie studentService;

	@GetMapping(value = "/all")
	public ResponseEntity<?> findAllStudent() {
		return ResponseEntity.ok(studentService.findAllStudent());
	}

	@GetMapping("/list")
	public ResponseEntity<?> getListStudent(
			@Valid @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@Valid @RequestParam(value = "perPage", defaultValue = "10", required = false) int perPage,
			@Valid @RequestParam(value = "searchField", required = false) String searchField,
			@Valid @RequestParam(value = "searchText", defaultValue = "", required = false) String searchText,
			@Valid @RequestParam(value = "sortField", required = false) String sortField,
			@Valid @RequestParam(value = "sortType", required = false) String sortType) {

		return ResponseEntity
				.ok(studentService.getListStudent(pageNo, perPage, searchText, searchField, sortField, sortType));
	}

	@PostMapping(value = "/insert")
	public ResponseEntity<?> insertStuednt(@RequestBody Student obj) {
		Student student = JSON.parseObject(JSON.toJSONString(obj), Student.class);
		return ResponseEntity.ok(studentService.insertStudent(student));
	}

	@GetMapping(value = "/find")
	public ResponseEntity<?> findBy() {
		return ResponseEntity.ok(studentService.findBy("name", "a", "24"));
	}

	@PostMapping(value = "/update")
	public ResponseEntity<?> updateStudent(@RequestBody Student student) {
		return ResponseEntity.ok(studentService.updateStudent(student));
	}

	@GetMapping(value = "/{studentId}")
	public ResponseEntity<?> deleteStudent(@PathVariable String studentId) {
		return ResponseEntity.ok(studentService.deleteStudent(studentId));
	}
}
