package com.ibm.training.bootcamp.rest.sample01.service;

import java.util.List;

import com.ibm.training.bootcamp.rest.sample01.domain.User;

public interface UserService {

	public List<User> findAll();
	
	public User find(Long id);
	
	public List<User> findByName(String firstName, String lastName);
	
	public void add(User user);
	
	public void upsert(User user);
	
	public void delete(Long id);

}
