package com.alan.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false), "Application continues as usual");
		
		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return responseEntity;
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException e, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false), "We couldn't locate the resource");
		
		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	
	
	@ExceptionHandler(UsersNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUsersNotFoundException(UsersNotFoundException e, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false), "We couldn't locate the resource");
		
		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	
	@ExceptionHandler(ExistingUserException.class)
	public final ResponseEntity<ExceptionResponse> handleExistingUserFoundException(ExistingUserException e, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false), "This user already exists");
		
		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
		
		return responseEntity;
	}
	
	@ExceptionHandler(PostNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleExistingUserFoundException(PostNotFoundException e, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false), "The post was not found");
		
		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "There was an error", ex.getBindingResult().toString(), null);
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}

	
}
