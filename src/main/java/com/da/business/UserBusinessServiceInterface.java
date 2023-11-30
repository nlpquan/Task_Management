package com.da.business;

import java.util.List;

import com.da.model.UserModel;

// Interface defining business operations related to users
public interface UserBusinessServiceInterface {
	
	// Retrieve a list of UserModels
	public List<UserModel> getUsers();
	
	// Create a new user and return a status indicator
	public int createUser(UserModel userModel);
	
	// Verify the uniqueness of a username and return a status indicator
	public int verifyUsername(UserModel userModel);
}
