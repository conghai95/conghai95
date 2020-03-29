//package com.example.demo;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.example.demo.modal.Student;
//import com.example.demo.service.StudentServie;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//class MyBatisExampleApplicationTests {
//
//	@Autowired
//	MockMvc mockMvc;
//
//	@Autowired
//	StudentServie studentService;
//
//	@Test
//	public void contextLoads() throws Exception {
//		List<Student> sts = studentService.findAllStudent();
//		assertThat(sts.size()).isGreaterThan(3);
//	}
//
//	@Test
//	public void studentServiceGetAllTest() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/student/all").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}
//
//	@Test
//	public void studentServceInsert() throws Exception {
//		String student = "{\"id\": \"546\", \"name\" : \"vinh\", \"phone\": \"0213124\"}";
//		mockMvc.perform(
//				MockMvcRequestBuilders.post("/student/insert").contentType(MediaType.APPLICATION_JSON).content(student))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}
//
//}
