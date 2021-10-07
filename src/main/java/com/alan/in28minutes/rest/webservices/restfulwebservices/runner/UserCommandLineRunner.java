package com.alan.in28minutes.rest.webservices.restfulwebservices.runner;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alan.in28minutes.rest.webservices.restfulwebservices.models.Post;
import com.alan.in28minutes.rest.webservices.restfulwebservices.models.User;
import com.alan.in28minutes.rest.webservices.restfulwebservices.repository.UserRepository;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user = new User("Juan",new Date());
		
		Post post = new Post("Hello all", new Date());
		
		user.getPosts().add(post);
		
		userRepository.save(user);
		
		log.info("All users:");
		
		userRepository.findAll().forEach(System.out::println);
	}

}
