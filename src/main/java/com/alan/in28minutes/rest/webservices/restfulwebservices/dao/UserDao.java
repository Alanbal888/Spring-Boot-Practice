package com.alan.in28minutes.rest.webservices.restfulwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.alan.in28minutes.rest.webservices.restfulwebservices.exception.ExistingUserException;
import com.alan.in28minutes.rest.webservices.restfulwebservices.exception.UsersNotFoundException;
import com.alan.in28minutes.rest.webservices.restfulwebservices.models.User;

@Component
public class UserDao {
	
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;
	
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}

	
	public List<User> findAll() throws UsersNotFoundException{
		
//		if(true)
//			throw new UsersNotFoundException("Users could not be retrieved");
//		
		return UserDao.users;
	}
	
	public User findOne(Integer id) {
		 List<User> filteredUsers = users.stream().filter(u -> u.getId() == id).collect(Collectors.toList());
		 if(!filteredUsers.isEmpty()) {
			 return filteredUsers.get(0);
		 }
		 else {
			 return null;
		}
	}
	
	public User save(User user) throws ExistingUserException{
		
		if(user.getId() == null)
			user.setId(++UserDao.usersCount);
		
		else if(this.findOne(user.getId()) != null) {
			throw new ExistingUserException("id="+user.getId());
		}
		
		UserDao.users.add(user);
		return user;
	}
	
	
	public User deleteById(int id) {
		
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		
		return null;
	}
	
}
