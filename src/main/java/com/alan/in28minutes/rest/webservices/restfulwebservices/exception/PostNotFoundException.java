package com.alan.in28minutes.rest.webservices.restfulwebservices.exception;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6664643205802898939L;

	public PostNotFoundException(String message) {
		super(message);
	}
	
}
