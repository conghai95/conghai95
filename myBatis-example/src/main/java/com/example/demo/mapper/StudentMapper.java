package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.modal.Student;

@Mapper
public interface StudentMapper {

	public List<Student> findAllStudent();

	public int insertStudent(Student student);

	public List<Student> findBy(String fileName, String name, String phone);

	public int updateStudent(Student student);

	public int deleteStudent(String studentId);

	public List<Student> getListStudents(int pageNo, int perPage, String searchText, String searchField,
			String sortField, String sortType);
}
