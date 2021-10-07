package com.alan.in28minutes.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.in28minutes.rest.webservices.restfulwebservices.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}
