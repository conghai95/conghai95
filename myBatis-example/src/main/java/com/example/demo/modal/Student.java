package com.example.demo.modal;

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
public class Student {

	private String studentId;

	private String name;

	private String phone;

	private LeadTeacher leadTeacher;

}
