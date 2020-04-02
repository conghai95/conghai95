package com.example.demo.modal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lead_teacher")
public class LeadTeacher {

	@Id
	@Column(name = "lead_teacher_id")
	private String leadTeacherId;

	@Column(name = "name")
	private String name;

	@Column(name = "phone_number")
	private String phone;

	@Column(name = "address")
	private String addr;

	@OneToMany(mappedBy = "leadTeacher", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Student> students;
}
