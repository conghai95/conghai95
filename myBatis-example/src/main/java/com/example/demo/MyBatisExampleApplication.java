package com.example.demo;

import javax.jms.JMSException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.demo.jms.SampleJmsMessageSender;
import com.example.demo.jms.SampleListener;
import com.example.demo.modal.Student;

@SpringBootApplication
public class MyBatisExampleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
//		SpringApplication.run(MyBatisExampleApplication.class, args);
		SampleJmsMessageSender sender = new SampleJmsMessageSender();
		SampleListener revicer = new SampleListener();

		sender.sendMessage(new Student("hai", "09283483"));
		try {
			System.out.println("message: " + revicer.receiveMessage());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
