package com.assysttech.TaskManager.service;

import com.assysttech.TaskManager.entity.Task;
import com.assysttech.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return this.taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id) {
        return this.taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        return this.taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        this.taskRepository.deleteById(id);
    }
}
