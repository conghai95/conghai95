package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.DataListResponse;
import com.example.demo.modal.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/search")
	public ResponseEntity<?> searchFields(
			@Valid @RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@Valid @RequestParam(value = "perPage", defaultValue = "10", required = false) int perPage,
			@Valid @RequestParam(value = "searchText", defaultValue = "", required = false) String searchText,
			@Valid @RequestParam(value = "sortField", required = false) String sortField,
			@Valid @RequestParam(value = "sortType", required = false) String sortType,
			@Valid @RequestParam(value = "searchField", required = false) String searchField,
			@Valid @RequestParam(value = "timeFrom", required = false) String timeForm,
			@Valid @RequestParam(value = "timeTo", required = false) String timeTo) {
		DataListResponse<Student> sts = studentService.getListStudent(page, perPage, searchText,
				sortField, sortType, searchField, timeForm, timeTo);

		return ResponseEntity.status(HttpStatus.OK).body(sts);
	}
}
