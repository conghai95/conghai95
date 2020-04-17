package com.example.demo.jms;

import java.util.Queue;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

import com.example.demo.modal.Student;

public class SampleJmsMessageSender {

	private JmsTemplate jmsTemplate;
	private Queue<?> queue;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setQueue(Queue<?> queue) {
		this.queue = queue;
	}

	public void simpleSend() {
		jmsTemplate.send((Destination) queue, s -> s.createTextMessage("hello queue world"));
	}

	public void sendMessage(Student st) {
		this.jmsTemplate.convertAndSend(st);
	}

	public void sendTextMessage(String msg) {
		this.jmsTemplate.send((Destination) queue, s -> s.createTextMessage(msg));
	}
}
