package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ResponseException<T> extends ResponseEntity<ResponseBodyException<T>> {

	public ResponseException(ResponseBodyException<T> body, MultiValueMap<String, String> headers, HttpStatus status) {
		super(body, headers, status);
	}

	public ResponseException(ResponseBodyException<T> body, HttpStatus status) {
		super(body, status);
	}
}
