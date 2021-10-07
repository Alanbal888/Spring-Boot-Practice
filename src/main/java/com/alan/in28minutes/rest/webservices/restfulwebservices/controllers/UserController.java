package com.alan.in28minutes.rest.webservices.restfulwebservices.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alan.in28minutes.rest.webservices.restfulwebservices.dao.UserDao;
import com.alan.in28minutes.rest.webservices.restfulwebservices.exception.ExistingUserException;
import com.alan.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.alan.in28minutes.rest.webservices.restfulwebservices.exception.UsersNotFoundException;
import com.alan.in28minutes.rest.webservices.restfulwebservices.models.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RequestMapping("users")
@RestController
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@GetMapping("/find-all")
	public List<User> findAllUsers() throws UsersNotFoundException{
		return userDao.findAll();
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> getUser(@PathVariable("id") int id) {
		User user = userDao.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id="+id);
		
		EntityModel<User> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = 
					linkTo(methodOn(this.getClass()).findAllUsers());
		
		model.add(linkToUsers.withRel("all-users"));
		
		return model;
	}
	
	@PostMapping(path="/add", consumes="application/json")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) throws ExistingUserException{
		User newUser = userDao.save(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(newUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") Integer id) {
		
		User user = userDao.deleteById(id);
		
		if(user == null)
			throw new UserNotFoundException("id="+id);
		
		return ResponseEntity.noContent().build();
	}
	
}
