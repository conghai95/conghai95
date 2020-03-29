package com.example.demo.service;

import java.util.List;
import com.example.demo.modal.Student;

public interface StudentServie {

	public List<Student> findAllStudent();

	public int insertStudent(Student st);

	public List<Student> findBy(String fileName, String name, String phone);
}
