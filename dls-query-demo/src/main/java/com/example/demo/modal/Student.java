package com.example.demo.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "student_id")
	private String studentId;

	@Column(name = "name")
	private String name;

	@Column(name = "phone_number")
	private String phone;

	@ManyToOne
	@JoinColumn(name = "lead_teacher_id")
	private LeadTeacher leadTeacher;

}
