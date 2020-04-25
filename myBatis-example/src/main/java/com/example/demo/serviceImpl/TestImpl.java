package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.modal.Student;
import com.example.demo.service.StudentServie;

@Service("TestImpl")
public class TestImpl implements StudentServie {

	@Override
	public List<Student> findAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findBy(String fileName, String name, String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertStudent(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStudent(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteStudent(String studentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> getListStudent(int pageNo, int perPage, String searchText, String searchField,
			String sortField, String sortType) {
		// TODO Auto-generated method stub
		return null;
	}

}
