package com.da.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.da.business.MainBusinessService;

import com.da.data.UserDataAccess;
import com.da.data.entity.UserEntity;
import com.da.model.UserModel;

@Service
public class SecurityService implements UserDetailsService {
	
	@Autowired
	UserDataAccess userDataService;
	
	@Autowired
	MainBusinessService mainBusinessService;
	
	@Autowired
	UserModel userModel;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userDataService.findByUsername(username);
		if(user != null)
		{
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("USERS"));
			mainBusinessService.createUserSession(username);
			return new User(user.getUsername(), user.getPassword(), authorities);
		}
		else
		{
			throw new UsernameNotFoundException("Invalid Username or password");
		}
	}

	
	/**
	 * This method checks if the username equals to the password of that username.
	 */
	/*
	public int authenticateUser(UserModel userModel) {
		UsersEntity usersEntity;
		if(usersDataService.findByUsername(userModel.credentials.getUsername()) == null) {
			return 1;
		} else {
			usersEntity = usersDataService.findByUsername(userModel.credentials.getUsername());
			if(userModel.credentials.getPassword().equals(usersEntity.getPassword())) {
				this.userModel.credentials.setUsername(usersEntity.getUsername());
				return 0;
			} else {
				return 1;
			}
		}
	}
	*/

}
