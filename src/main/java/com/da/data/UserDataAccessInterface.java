package com.da.data;

import java.util.List;

public interface UserDataAccessInterface <T>{
	
	// Find all 
	public List<T> findAll();
	//Find by username
	public T findByUsername(String username);
	// Create method
	public boolean create(T t);
	// Update method
	public boolean update(T t);
	// Delete method
	public boolean delete(T t);
}
