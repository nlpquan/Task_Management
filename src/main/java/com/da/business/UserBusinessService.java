package com.da.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.da.data.UserDataAccess;
import com.da.data.entity.UserEntity;
import com.da.model.UserModel;

@Service
public class UserBusinessService implements UserBusinessServiceInterface{

	@Autowired
	UserDataAccess userDataService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserModel userModel;
	
	@Override
	public List<UserModel> getUsers() {
		List<UserEntity> userEntity = userDataService.findAll();
		
		List<UserModel> userDomain = new ArrayList<UserModel>();
		for(UserEntity entity : userEntity)
		{
			userDomain.add(new UserModel(
					entity.getId(), 
					entity.getUsername(), 
					entity.getPassword(), 
					entity.getFirstName(), 
					entity.getLastName()));
		}
		// Return list of Domain Orders
		return userDomain;
	}

	@Override
	public int createUser(UserModel userModel) {
		
		String encodedPassword = passwordEncoder.encode(userModel.getCredentials().getPassword());
		
		UserEntity userEntity = new UserEntity(userModel.getId(),
				userModel.credentials.getUsername(),
				//userModel.credentials.getPassword(),
				encodedPassword,
				userModel.getFirstName(), 
				userModel.getLastName());
		userDataService.create(userEntity);

    return 0;
	}
	
	@Override
	/**
	 * This method updates user data that comes from the database and updates the instance of a usermodel with the current user
	 */
	public UserModel createUserSession(String username) {
		UserEntity usersEntity = userDataService.findByUsername(username);
		// update the usermodel from database data
			userModel.setId(usersEntity.getId());
			userModel.credentials.setUsername(usersEntity.getUsername());
			userModel.credentials.setPassword(usersEntity.getPassword());
			userModel.setFirstName(usersEntity.getFirstName());
			userModel.setLastName(usersEntity.getLastName());
			
			return userModel;
	}

	@Override
	public int verifyUsername(UserModel userModel) {
		if(userDataService.findByUsername(userModel.credentials.getUsername()) == null) {
			return 0;
		}
		return 1;
	}

}
