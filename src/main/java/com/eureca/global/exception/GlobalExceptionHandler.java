package com.eureca.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eureca.dto.ApiErrorResponse;
import com.eureca.exception.ResourceNotFoundException;
import com.eureca.exception.UserServiceException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 404 - Resource Not Found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleResourceNotFound(ResourceNotFoundException ex,
			HttpServletRequest request) {

		ApiErrorResponse error = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), "RESOURCE_NOT_FOUND",
				ex.getMessage(), request.getRequestURI());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserServiceException.class)
	public ResponseEntity<ApiErrorResponse> handleUserResourceNotFound(UserServiceException ex,
			HttpServletRequest request) {
		
		ApiErrorResponse error = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), "RESOURCE_NOT_FOUND",
				ex.getMessage(), request.getRequestURI());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// 400 - Validation Errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

		ApiErrorResponse error = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "VALIDATION_FAILED", message,
				request.getRequestURI());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// 500 - Generic Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleGlobalException(Exception ex, HttpServletRequest request) {

		ApiErrorResponse error = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR",
				ex.getMessage(), request.getRequestURI());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

