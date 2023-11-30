package com.da.model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@Component
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message="This field is required")
	private String firstName;
	
	@NotEmpty(message="This field is required")
	private String lastName;
	
	@Valid public UserCredential credentials;

	public UserModel() {
		credentials = new UserCredential();
	}

	public UserModel(String firstName, String lastName) {
		credentials = new UserCredential();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UserModel(long id, String username, String password, String firstName, String lastName) {
		credentials = new UserCredential();
		this.id = id;
		this.credentials.setUsername(username);
		this.credentials.setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
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
	
	/**
	 * getter for credentials
	 * @return credentials
	 */
	public UserCredential getCredentials() {
		return credentials;
	}
	/**
	 * getter for credentials
	 * @param credentials credentials
	 */
	public void setCredentials(UserCredential credentials) {
		this.credentials = credentials;
	}

	@Override
	public String toString() {
	    return String.format("User [\n  id=%d,\n  firName='%s',\n  lasName='%s',\n]", 
	                         id, firstName, lastName);
	}

}
