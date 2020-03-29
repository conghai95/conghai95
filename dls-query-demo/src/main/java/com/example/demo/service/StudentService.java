package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.modal.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> getListStudent(Integer pageNo, Integer pageSize, String fieldSearch, String textSearch,
			String sortField, String sortType) {
		List<Student> sts = null;
		Sort sort = getSort(sortField, sortType);
		Pageable paging = getPageable(pageNo, pageSize, sortField, sortType);

		if (paging != null) {
			Page<Student> pagedResult = studentRepository.findAll(searchStudents(fieldSearch, textSearch), paging);
			if (pagedResult.hasContent()) {
				sts = pagedResult.getContent();
			}
			return sts;
		} else if (sort != null) {
			return studentRepository.findAll(searchStudents(fieldSearch, textSearch), sort);
		}
		return studentRepository.findAll(searchStudents(fieldSearch, textSearch));
	}

	public Specification<Student> searchStudents(String fieldSearch, String textSearch) {
		return new Specification<Student>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				try {
					if (textSearch != null) {
						predicates.add(criteriaBuilder
								.and(criteriaBuilder.like(root.get(fieldSearch), "%" + textSearch + "%")));
					}
				} catch (Exception e) {
					System.out.println("fieldSearch'sparameter is wrong format!");
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	public Pageable getPageable(Integer pageNo, Integer pageSize, String sortField, String sortType) {
		if (pageNo == null || pageSize == null) {
			return null;
		}
		Sort sort = getSort(sortField, sortType);
		if (sort != null) {
			return PageRequest.of(pageNo, pageSize, sort);
		}
		return PageRequest.of(pageNo, pageSize);
	}

	public Sort getSort(String sortField, String sortType) {
		if (sortField == null || sortType == null) {
			return null;
		}
		if ("DESC".equals(sortType)) {
			return Sort.by(Direction.DESC, sortField);
		} else if ("ASC".equals(sortType)) {
			return Sort.by(Direction.ASC, sortField);
		}
		return null;
	}

}
