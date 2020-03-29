package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.modal.Student;

@Mapper
public interface StudentMapper {

	public List<Student> findAllStudent();

	public int insertStudent(Student st);
}
