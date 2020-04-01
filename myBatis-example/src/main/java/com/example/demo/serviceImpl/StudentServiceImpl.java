package com.example.demo.serviceImpl;

import java.util.ArrayList;
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

	@Override
	public List<Student> findBy(String fileName, String name, String phone) {
		return studentMapper.findBy(fileName, name, phone);
	}

	@Override
	public int insertStudent(Student student) {
		return studentMapper.insertStudent(student);
	}

	@Override
	public int updateStudent(Student student) {
		return studentMapper.updateStudent(student);
	}

	@Override
	public int deleteStudent(String studentId) {
		return studentMapper.deleteStudent(studentId);
	}

	@Override
	public List<Student> getListStudent(int pageNo, int perPage, String searchText, String searchField,
			String sortField, String sortType) {
		List<Student> sts = new ArrayList<Student>();
		if (searchField != null && !searchField.isEmpty() && searchText != null && !searchText.isEmpty()) {
			sts = studentMapper.getListStudents(pageNo, perPage, searchText, searchField, sortField, sortType);
		}
		return sts;
	}
}
