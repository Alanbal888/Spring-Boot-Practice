package com.alan.in28minutes.rest.webservices.restfulwebservices.exception;

public class UsersNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 4271909333184687628L;

	public UsersNotFoundException(String message) {
		super(message);
	}

}
