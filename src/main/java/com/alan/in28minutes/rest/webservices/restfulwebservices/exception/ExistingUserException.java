package com.alan.in28minutes.rest.webservices.restfulwebservices.exception;

public class ExistingUserException extends RuntimeException{

	private static final long serialVersionUID = -2497755719928662381L;

	public ExistingUserException(String message) {
		super(message);
	}
	
}
