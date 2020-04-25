package com.example.demo.jms;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.example.demo.modal.Student;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

public class SampleMessageConverter implements MessageConverter {

	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		Student employee = (Student) object;
		MapMessage message = session.createMapMessage();
		message.setString("name", employee.getName());
		message.setString("age", employee.getPhone());
		return message;
	}

	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		MapMessage mapMessage = (MapMessage) message;
		return new Student(mapMessage.getString("name"), mapMessage.getString("phone"));
	}
}
