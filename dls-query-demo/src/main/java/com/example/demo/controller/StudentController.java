package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/search")
	public ResponseEntity<List<Student>> searchFields(
			@Valid @RequestParam(value = "fieldSearch", required = false) String fieldSearch,
			@Valid @RequestParam(value = "textSearch", required = false) String textSearch,
			@Valid @RequestParam(value = "pageNo", required = false) Integer pageNo,
			@Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
			@Valid @RequestParam(value = "sortField", required = false) String sortField,
			@Valid @RequestParam(value = "sortType", required = false) String sortType) {
		List<Student> sts = studentService.getListStudent(pageNo, pageSize, fieldSearch, textSearch, sortField,
				sortType);
		for (Student st : sts) {
			System.out.println(st);
		}
		return ResponseEntity.status(HttpStatus.OK).body(sts);
	}
}
