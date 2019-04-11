package com.ibm.training.bootcamp.rest.sample01.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.sample01.dao.UserDao;
import com.ibm.training.bootcamp.rest.sample01.dao.UserHashMapDaoImpl;
import com.ibm.training.bootcamp.rest.sample01.dao.UserJdbcDaoImpl;
import com.ibm.training.bootcamp.rest.sample01.domain.User;

public class UserServiceImpl implements UserService{
	
	UserDao userDao;

	public UserServiceImpl() {
		this.userDao = UserJdbcDaoImpl.getInstance();
		//this.userDao = UserHashMapDaoImpl.getInstance();
	}
	
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User find(Long id) {
		return userDao.find(id);
	}

	@Override
	public List<User> findByName(String firstName, String lastName) {
		return userDao.findByName(firstName, lastName);
	}

	@Override
	public void add(User user) {
		if (validate(user)) {
			userDao.add(user);
		} else {
			throw new IllegalArgumentException("Fields firstName and lastName cannot be blank.");
		}
	}

	@Override
	public void upsert(User user) {
		if (validate(user)) {
			if(user.getId() != null && user.getId() >= 0) {
				userDao.update(user);
			} else {
				userDao.add(user);
			}
		} else {
			throw new IllegalArgumentException("Fields firstName and lastName cannot be blank.");
		}
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}
	
	private boolean validate(User user) {
		return !StringUtils.isAnyBlank(user.getFirstName(), user.getLastName());
	}

}
