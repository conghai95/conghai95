package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping(value = "/insert")
	public ResponseEntity<?> insertStuednt(@RequestBody Student obj) {
		Student student = JSON.parseObject(JSON.toJSONString(obj), Student.class);
		return ResponseEntity.ok(studentService.insertStudent(student));
	}

	@GetMapping(value = "/find")
	public ResponseEntity<?> findBy() {
		return ResponseEntity.ok(studentService.findBy("name", "a", "24"));
	}
}
