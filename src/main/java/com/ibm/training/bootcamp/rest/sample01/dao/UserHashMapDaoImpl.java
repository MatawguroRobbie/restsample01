package com.ibm.training.bootcamp.rest.sample01.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.sample01.domain.User;

@Singleton
public class UserHashMapDaoImpl implements UserDao {

	static private UserHashMapDaoImpl INSTANCE;
	static private final Map<Long, User> USER_STORE;
	static private long id = 0;

	static {
		USER_STORE = new HashMap<>();
		User user1 = new User(id++, "Tony", "Stark");
		User user2 = new User(id++, "Steve", "Rogers");
		User user3 = new User(id++, "Peter", "Parker");
		USER_STORE.put(user1.getId(), user1);
		USER_STORE.put(user2.getId(), user2);
		USER_STORE.put(user3.getId(), user3);
	}

	private UserHashMapDaoImpl() {
		
	}
	
	static public UserHashMapDaoImpl getInstance( ) {
		
		UserHashMapDaoImpl instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new UserHashMapDaoImpl();
			INSTANCE = instance;
		}
		
		return instance;
	}
	
	@Override
	public List<User> findAll() {
		return new ArrayList<User>(USER_STORE.values());
	}

	@Override
	public User find(Long id) {
		return USER_STORE.get(id);
	}

	@Override
	public List<User> findByName(String firstName, String lastName) {
		List<User> users = USER_STORE.values().stream()
				.filter(user -> StringUtils.isBlank(firstName) || user.getFirstName().equalsIgnoreCase(firstName))
				.filter(user -> StringUtils.isBlank(lastName) || user.getLastName().equalsIgnoreCase(lastName))
				.collect(Collectors.toList());
		return users;
	}

	@Override
	public void add(User user) {
		if (user != null && user.getId() == null) {
			user.setId(id++);
			USER_STORE.put(user.getId(), user);
		}
	}

	@Override
	public void update(User user) {
		if (user != null && user.getId() != null) {
			USER_STORE.put(user.getId(), user);
		}
	}

	@Override
	public void delete(Long id) {
		if (id != null) {
			USER_STORE.remove(id);
		}
	}

}
