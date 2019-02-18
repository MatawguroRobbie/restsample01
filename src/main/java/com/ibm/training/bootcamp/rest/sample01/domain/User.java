package com.ibm.training.bootcamp.rest.sample01.domain;

public class User {

	Long id;
	private String firstName;
	private String lastName;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName) {
		this(null, firstName, lastName);
	}

	public User(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
