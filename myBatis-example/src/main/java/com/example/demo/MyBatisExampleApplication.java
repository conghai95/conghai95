package com.example.demo;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MyBatisExampleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
		System.out.println("uuid: " + uuid);
		SpringApplication.run(MyBatisExampleApplication.class, args);
	}

}
