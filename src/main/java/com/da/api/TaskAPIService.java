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

    // Retrieve all tasks or tasks by title
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskModel>> getAllTasks(@RequestParam(required = false) String title) {

        List<TaskModel> tasks = new ArrayList<TaskModel>();

        if (title == null)
            // Get all tasks if no title parameter is provided
            taskRepository.findAll().forEach(tasks::add);
        else
            // Get tasks by title if title parameter is provided
            taskRepository.findByTitle(title).forEach(tasks::add);

        if (tasks.isEmpty()) {
            // Return no content status if no tasks are found
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Retrieve a task by ID
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable("id") long id) {
        // Find task by ID or throw exception if not found
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + id));

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    // Create a new task
    @PostMapping("/tasks")
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel task) {
        // Save the new task and return it with a created status
        TaskModel _task = taskRepository
                .save(new TaskModel(task.getTitle(), task.getDescription(), false));
        return new ResponseEntity<>(_task, HttpStatus.CREATED);
    }

    // Update an existing task by ID
    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable("id") long id, @RequestBody TaskModel task) {
        // Find the existing task by ID or throw exception if not found
        TaskModel _task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + id));
        // Update task details and return it with an OK status
        _task.setTitle(task.getTitle());
        _task.setDescription(task.getDescription());
        _task.setCompleted(task.isCompleted());
        return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
    }

    // Delete a task by ID
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
        // Delete the task by ID and return no content status
        taskRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete all tasks
    @DeleteMapping("/tasks")
    public ResponseEntity<HttpStatus> deleteAllTasks() {
        // Delete all tasks and return no content status
        taskRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Retrieve all completed tasks
    @GetMapping("/tasks/completed")
    public ResponseEntity<List<TaskModel>> findByCompleted() {

        List<TaskModel> tasks = taskRepository.findByCompleted(true);

        if (tasks.isEmpty()) {
            // Return no content status if no completed tasks are found
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
