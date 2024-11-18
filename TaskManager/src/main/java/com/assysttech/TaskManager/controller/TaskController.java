package com.assysttech.TaskManager.controller;

import com.assysttech.TaskManager.entity.Task;
import com.assysttech.TaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return this.taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = this.taskService.findTaskById(id);
        if(task.isPresent()) {
            return ResponseEntity.ok(task.get());
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(this.taskService.saveTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Optional<Task> task = this.taskService.findTaskById(id);
        if(task.isPresent()) {
            Task updatedTask = task.get();
            updatedTask.setProject(taskDetails.getProject());
            updatedTask.setCompleted(taskDetails.getCompleted());
            updatedTask.setName(taskDetails.getName());
            updatedTask.setDescription(taskDetails.getDescription());
            updatedTask.setDueDate(taskDetails.getDueDate());
            return ResponseEntity.ok(this.taskService.saveTask(updatedTask));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        this.taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
