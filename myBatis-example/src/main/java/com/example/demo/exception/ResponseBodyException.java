package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

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
public class ResponseBodyException<T> {

	private HttpStatus status;
	private String message;
	private T data;
	private Date timestamp = new Date();
	private String path;

	public ResponseBodyException(HttpStatus status, String message, T data, String path) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.path = path;
	}
}
