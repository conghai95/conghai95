package com.example.demo.service;

import java.util.List;
import com.example.demo.modal.Student;

public interface StudentServie {

	public List<Student> findAllStudent();

	public int insertStudent(Student st);
}
