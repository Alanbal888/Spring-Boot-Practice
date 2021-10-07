package com.alan.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private String details;
	private String comment;
	
	
	
	public String getComment() {
		return comment;
	}

	public ExceptionResponse(Date timestamp, String message, String details, String comment) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.comment = comment;
	}

	public Date getTimestamp() {
		return timestamp;
	}



	public String getMessage() {
		return message;
	}



	public String getDetails() {
		return details;
	}
	
}
