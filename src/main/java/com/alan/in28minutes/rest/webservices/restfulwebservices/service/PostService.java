package com.alan.in28minutes.rest.webservices.restfulwebservices.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alan.in28minutes.rest.webservices.restfulwebservices.dao.PostDao;
import com.alan.in28minutes.rest.webservices.restfulwebservices.dao.UserDao;
import com.alan.in28minutes.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.alan.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.alan.in28minutes.rest.webservices.restfulwebservices.models.Post;
import com.alan.in28minutes.rest.webservices.restfulwebservices.models.User;

@Component
public class PostService {
	
	PostDao postDao;
	UserDao userDao;
	
	@Autowired
	public PostService(PostDao postDao, UserDao userDao) {
		this.postDao = postDao;
		this.userDao = userDao;
	}
	
	public List<Integer> getUserPosts(int id) throws UserNotFoundException{
		
		User user = userDao.findOne(id);
		
		if(user != null) {
			return user.getPosts().stream().map(p -> p.getId()).collect(Collectors.toList());
		}
		
		else throw new UserNotFoundException("id="+id);
		
	}
	
	public Post saveUserPost(int id, Post post) throws UserNotFoundException{
		
		User user = userDao.findOne(id);
		
		if(user != null) {
			if(post.getId() == null)
				post.setId(++PostDao.postCount);
			user.getPosts().add(post);
			return post;
		}
		
		else throw new UserNotFoundException("id="+id);
	}
	
	public Post getUserPost(int userId, int postId) {
		
		User user = userDao.findOne(userId);
		
		if(user != null) {
		
			 List<Post> filteredPosts = user.getPosts().stream().filter(p -> p.getId() == postId).collect(Collectors.toList());
			
			 if(!filteredPosts.isEmpty()) {
				 return filteredPosts.get(0);
			 }
			 
			 else
				 throw new PostNotFoundException("post_id="+postId);
		}
		
		else throw new UserNotFoundException("id="+userId);
		
	}
	
	

}
