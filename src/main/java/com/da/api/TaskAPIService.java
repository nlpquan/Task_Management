package com.da.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.da.exception.ResourceNotFoundException;
import com.da.model.TaskModel;
import com.da.repository.TaskRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TaskAPIService {

	@Autowired
	TaskRepository taskRepository;

	@GetMapping("/tasks")
	public ResponseEntity<List<TaskModel>> getAllTasks(@RequestParam(required = false) String title) {
	
		List<TaskModel> tasks = new ArrayList<TaskModel>();

		if (title == null)
			taskRepository.findAll().forEach(tasks::add);
		else
			taskRepository.findByTitle(title).forEach(tasks::add);

		if (tasks.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<TaskModel> getTaskById(@PathVariable("id") long id) {
		TaskModel task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + id));

		return new ResponseEntity<>(task, HttpStatus.OK);		
	}

	@PostMapping("/tasks")
	public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel task) {
		TaskModel _task = taskRepository
				.save(new TaskModel(task.getTitle(), task.getDescription(), false));
		return new ResponseEntity<>(_task, HttpStatus.CREATED);
		
	}

	@PutMapping("/tasks/{id}")
	public ResponseEntity<TaskModel> updateTask(@PathVariable("id") long id, @RequestBody TaskModel task) {
		TaskModel _task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + id));		
			_task.setTitle(task.getTitle());
			_task.setDescription(task.getDescription());
			_task.setCompleted(task.isCompleted());
			return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
	}

	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
		taskRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}

	@DeleteMapping("/tasks")
	public ResponseEntity<HttpStatus> deleteAllTasks() {
		taskRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/tasks/completed")
	public ResponseEntity<List<TaskModel>> findByCompleted() {

		List<TaskModel> tasks = taskRepository.findByCompleted(true);

		if (tasks.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	
	}

}
