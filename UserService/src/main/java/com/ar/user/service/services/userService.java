package com.ar.user.service.services;

import java.util.List;

import com.ar.user.service.entities.User;

public interface userService {
	
	//user operations
	
	//create 
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single user of given id
	User getUser(String userId);
	
	//TODO:delete
	//TODO:update
}
