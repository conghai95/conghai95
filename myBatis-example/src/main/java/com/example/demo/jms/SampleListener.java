package com.example.demo.jms;

import java.util.Map;
import java.util.Queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;

import com.example.demo.modal.Student;

public class SampleListener implements MessageListener {

	private JmsTemplate jmsTemplate;
	private Queue<?> queue;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setQueue(Queue<?> queue) {
		this.queue = queue;
	}

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				String msg = ((TextMessage) message).getText();
				System.out.println("Received message: " + msg);
				if (msg == null) {
					throw new IllegalArgumentException("Null value received...");
				}
			} catch (JMSException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	public Student receiveMessage() throws JMSException {
		Map<?, ?> map = (Map<?, ?>) this.jmsTemplate.receiveAndConvert();
		return new Student((String) map.get("name"), (String) map.get("phone"));
	}
}
