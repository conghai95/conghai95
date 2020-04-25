package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AppCustomException;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.modal.Student;
import com.example.demo.service.StudentServie;
import com.github.pagehelper.PageHelper;

@Service
public class StudentServiceImpl implements StudentServie {

	@Autowired
	private StudentMapper studentMapper;

	@RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
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
	@Secured("ROLE_ADMIN")
	public int deleteStudent(String studentId) {
		return studentMapper.deleteStudent(studentId);
	}

	@Override
	public List<Student> getListStudent(int pageNo, int perPage, String searchText, String searchField,
			String sortField, String sortType) {
		if (!getAllOfRole().contains("ROLE_ADMIN")) {
			throw new AppCustomException("you need admin role to access this action!", HttpStatus.FORBIDDEN);
		}
		PageHelper.startPage(pageNo, perPage);
		List<Student> sts = studentMapper.getListStudents(searchText, searchField, sortField, sortType);
		return sts;
	}

	public List<String> getAllOfRole() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(e -> e.toString())
				.collect(Collectors.toList());
	}
}
