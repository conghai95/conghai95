package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.modal.Student;
import com.example.demo.service.StudentServie;

@Service
public class StudentServiceImpl implements StudentServie {

	@Autowired
	private StudentMapper studentMapper;

	public List<Student> findAllStudent() {
		return studentMapper.findAllStudent();
	}

	public int insertStudent(Student st) {
		return studentMapper.insertStudent(st);
	}
}
