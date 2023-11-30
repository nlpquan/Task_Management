package com.da.business;

import java.util.List;

import com.da.model.UserModel;

public interface UserBusinessServiceInterface {
	public List<UserModel> getUsers();
	public int createUser(UserModel userModel);
	public UserModel createUserSession(String username);
	public int verifyUsername(UserModel userModel);
}
