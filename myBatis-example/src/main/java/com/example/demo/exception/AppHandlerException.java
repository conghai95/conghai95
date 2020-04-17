package com.example.demo.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AppHandlerException extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		ResponseBodyException<?> responseError = new ResponseBodyException<>(status, ex.getMessage(), null,
				((ServletRequestAttributes) request).getRequest().getRequestURI());
		return handleExceptionInternal(ex, responseError, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		ResponseBodyException<?> responseError = new ResponseBodyException<>(status, ex.getMessage(), null,
				((ServletRequestAttributes) request).getRequest().getRequestURI());
		return handleExceptionInternal(ex, responseError, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}

	@ExceptionHandler({ AppCustomException.class })
	public ResponseException<?> handlerCustomException(AppCustomException ex, WebRequest request) {
		HttpStatus status = ex.getStatus() != null? ex.getStatus() : HttpStatus.BAD_REQUEST;
		return new ResponseException<>(new ResponseBodyException<String>(status, ex.getMessage(), null,
				((ServletRequestAttributes) request).getRequest().getRequestURI()), HttpStatus.BAD_REQUEST);
	}

	// 500
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handlerBadRequestException(Exception ex, WebRequest request) {
//		ResponseException<?> res = new ResponseException<>(
//				new ResponseBodyException<String>(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null,
//						((ServletRequestAttributes) request).getRequest().getRequestURI()),
//				HttpStatus.INTERNAL_SERVER_ERROR);
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	// 409
	@ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
	protected ResponseEntity<?> handleConflict(final RuntimeException ex, final WebRequest request) {
		return new ResponseException<>(new ResponseBodyException<String>(HttpStatus.CONFLICT, ex.getMessage(), null,
				((ServletRequestAttributes) request).getRequest().getRequestURI()), HttpStatus.CONFLICT);
	}
}
