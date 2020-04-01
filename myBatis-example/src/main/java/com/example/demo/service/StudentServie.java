package com.example.demo.service;

import java.util.List;
import com.example.demo.modal.Student;

public interface StudentServie {

	public List<Student> findAllStudent();

	public List<Student> findBy(String fileName, String name, String phone);

	public int insertStudent(Student student);

	public int updateStudent(Student student);

	public int deleteStudent(String studentId);

	public List<Student> getListStudent(int pageNo, int perPage, String searchText, String searchField,
			String sortField, String sortType);
}
