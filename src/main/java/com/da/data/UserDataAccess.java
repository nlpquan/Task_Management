package com.da.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.da.data.entity.UserEntity;

import com.da.repository.UserRepository;

@Service
public class UserDataAccess implements UserDataAccessInterface<UserEntity>{

	@Autowired
	private UserRepository userRepository;
	
	public UserDataAccess(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<UserEntity>();
		
		try
		{
			// Get all the entity orders
			Iterable<UserEntity> usersIterable = userRepository.findAll();
			
			// COnvert to a List and return the List
			users = new ArrayList<UserEntity>();
			usersIterable.forEach(users::add);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public UserEntity findByUsername(String username) {
		UserEntity user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public boolean create(UserEntity user) {
		try {
			this.userRepository.save(user);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(UserEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(UserEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

}
