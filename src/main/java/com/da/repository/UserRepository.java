package com.da.repository;

import org.springframework.data.repository.CrudRepository;

import com.da.data.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
	
	UserEntity findByUsername(String username);
}
