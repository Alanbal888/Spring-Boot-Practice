package com.alan.in28minutes.rest.webservices.restfulwebservices.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alan.in28minutes.rest.webservices.restfulwebservices.models.Post;
import com.alan.in28minutes.rest.webservices.restfulwebservices.service.PostService;

@RequestMapping("users/{id}/posts")
@RestController
public class PostController {


	private PostService postService;
	
	@Autowired
	public PostController(PostService postDao) {
		this.postService = postDao;
	}
	
	@GetMapping
	public List<Integer> getUserPosts(@PathVariable("id") int id){
		return postService.getUserPosts(id);
	} 
	
	@PostMapping
	public ResponseEntity<Object> saveUserPost(@PathVariable("id") int id, @RequestBody Post post){
		
		Post newPost = postService.saveUserPost(id, post);

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{postId}")
			.buildAndExpand(newPost.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{post_id}")
	public Post getPostDetails(@PathVariable("id") int userId, @PathVariable("post_id") int postId) {
		return postService.getUserPost(userId, postId);
	}
}
