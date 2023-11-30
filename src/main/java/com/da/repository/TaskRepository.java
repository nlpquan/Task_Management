package com.da.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.da.model.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
	
	List<TaskModel> findByTitle(String title);
	
	List<TaskModel> findByCompleted(boolean completed);
}
