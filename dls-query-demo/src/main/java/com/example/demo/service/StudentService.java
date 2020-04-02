package com.example.demo.service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
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

import com.example.demo.modal.DataListResponse;
import com.example.demo.modal.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public final String START_DATE = "startDate";
	public final String nameField = "name";
	public final String leadTeacherField = "leadTeacher";

	public DataListResponse<Student> getListStudent(int page, int perPage, String searchText, String sortField,
			String sortType, String searchField, String timeForm, String timeTo) {

		DataListResponse<Student> pageAble = new DataListResponse<Student>();
		Pageable paging = getPageable(page, perPage, sortField, sortType);

		Page<Student> pagedResult = studentRepository
				.findAll(getStudents(searchField, searchText, sortField, sortType, timeForm, timeTo), paging);
		if (pagedResult.hasContent()) {
			pageAble.setData(pagedResult.getContent());
			pageAble.setTotalPage(pagedResult.getTotalPages());
			pageAble.setTotalElement((int) pagedResult.getTotalElements());
			return pageAble;
		}
		return new DataListResponse<Student>(0, 0, new ArrayList<Student>());
	}

	public Specification<Student> getStudents(String searchField, String searchText, String sortField, String sortType,
			String timeForm, String timeTo) {

		List<Predicate> predicates = new ArrayList<Predicate>();

		return new Specification<Student>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				try {
					if (searchField != null && !searchField.isEmpty() && searchText != null && !searchText.isEmpty()) {

						String[] fields = searchField.split(",");
						String[] text = searchText.split(",");

						if (fields.length == 2 && text.length == 2) {
							if (nameField.equals(fields[0]) && leadTeacherField.equals(fields[1])) {
								predicates.add(criteriaBuilder.and(
										criteriaBuilder.like(root.get(nameField), "%" + text[0] + "%"), criteriaBuilder
												.like(root.get(leadTeacherField).get(nameField), "%" + text[1] + "%")));
							} else if (leadTeacherField.equals(fields[0]) && nameField.equals(fields[1])) {
								predicates.add(criteriaBuilder.and(
										criteriaBuilder.like(root.get(nameField), "%" + text[1] + "%"), criteriaBuilder
												.like(root.get(leadTeacherField).get(nameField), "%" + text[0] + "%")));
							}
						} else if (leadTeacherField.equals(searchField)) {
							predicates.add(criteriaBuilder.and(criteriaBuilder
									.like(root.get(leadTeacherField).get(nameField), "%" + searchText + "%")));
						} else if (nameField.equals(searchField)) {
							predicates.add(criteriaBuilder
									.and(criteriaBuilder.like(root.get(nameField), "%" + searchText + "%")));
						}
					}
				} catch (Exception e) {
				}

				// filter by date
				try {
					if (timeForm != null && !timeForm.isEmpty() && timeTo != null && !timeTo.isEmpty()) {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						predicates.add(criteriaBuilder.and(
								criteriaBuilder.greaterThanOrEqualTo(root.get(START_DATE), formatter.parse(timeForm)),
								criteriaBuilder.lessThanOrEqualTo(root.get(START_DATE), formatter.parse(timeTo))));
					}
				} catch (Exception e) {
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	public Pageable getPageable(int page, int perPage, String sortField, String sortType) {
		Sort sort = getSort(sortField, sortType);
		if (sort != null) {
			return PageRequest.of(page, perPage, sort);
		}
		return PageRequest.of(page, perPage);
	}

	public Sort getSort(String sortField, String sortType) {
		if (sortField == null || sortField.isEmpty() || sortType == null || sortType.isEmpty()) {
			return null;
		}
		if ("DESC".equals(sortType)) {
			if (isNumericType(sortField)) {
				return Sort.by(Direction.ASC, sortField);
			}
			return Sort.by(Direction.DESC, sortField);
		} else if ("ASC".equals(sortType)) {
			if (isNumericType(sortField)) {
				return Sort.by(Direction.DESC, sortField);
			}
			return Sort.by(Direction.ASC, sortField);
		}
		return null;
	}

	public boolean isNumericType(String sortField) {
		try {
			Field field = Student.class.getDeclaredField(sortField);
			if (field.getType().isPrimitive()) {
				return true;
			}
		} catch (NoSuchFieldException e) {
		} catch (SecurityException e) {
		}
		return false;
	}

}
