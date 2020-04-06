package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.modal.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class DlsQueryDemoApplicationTests {

	@TestConfiguration
	static class DlsQueryDemoApplicationTestsConfiguration {
		@Bean
		public StudentService studentService() {
			return new StudentService();
		}
	}

	@Autowired
	private StudentService studentService;

	@MockBean
	private StudentRepository studentRepository;

	@Before
	public void setUp() {
		List<Student> sts = studentRepository.findAll();
		Mockito.when(studentRepository.findAll()).thenReturn(sts);
	}

	@Test
	public void getListStudent_test() {
		List<Student> sts = studentRepository.findAll();
		System.out.println("sts: " + sts);
		assertThat(sts.size()).isEqualTo(0);
	}

}
