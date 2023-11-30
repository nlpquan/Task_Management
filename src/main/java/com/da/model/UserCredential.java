package com.da.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
public class UserCredential {
	
	@Size(min=1, max=32, message="Username must be between 1-32 characters")
	private String username;
	
	@Size(min=6, max=32, message="Password must be between 6-32 characters")
	private String password;
	
	public UserCredential() {
		username = null;
		password = null;
	}
	
	public String getUsername() {
		return username;
	}
	/**
	 * setter for username
	 * @param username username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * getter for password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * setter for password
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
