package com.da.data;

import java.util.List;

public interface UserDataAccessInterface <T>{
	public List<T> findAll();
	public T findByUsername(String username);
	public boolean create(T t);
	public boolean update(T t);
	public boolean delete(T t);
}
