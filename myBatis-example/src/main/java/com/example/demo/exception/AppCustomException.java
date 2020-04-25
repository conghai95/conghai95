package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AppCustomException extends RuntimeException {

	private String message;
	private HttpStatus status;

	public AppCustomException(String message) {
		super(message);
		this.message = message;
	}

	public AppCustomException(String message, HttpStatus status) {
		super(message);
		this.message = message;
		this.status = status;
	}
}
