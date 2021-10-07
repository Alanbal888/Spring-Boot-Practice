package com.alan.in28minutes.rest.webservices.restfulwebservices.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String content;
	private Date timestamp;
	@ManyToOne
	private User user;

	
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Post(String content, Date timestamp) {
		super();
		this.content = content;
		this.timestamp = timestamp;
	}


	public Post(Integer id, String content, Date timestamp) {
		super();
		this.id = id;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
