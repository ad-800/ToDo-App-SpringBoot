package com.group2.springtodoapp.controller;

import com.group2.springtodoapp.repo.TaskRepo;
import com.group2.springtodoapp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.group2.springtodoapp.exception.TaskNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class TaskController {
    @Autowired
    private TaskRepo taskRepo;

    @PostMapping("/task")
    Task newTask(@RequestBody Task newTask) {
        return taskRepo.save(newTask);
    }

    @GetMapping("/tasks")
    List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @GetMapping("/task/{id}")
    Task getTaskById(@PathVariable Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PutMapping("/task/{id}")
    Task updateTask(@RequestBody Task newTask, @PathVariable Long id) {
        return taskRepo.findById(id)
                .map(task -> {
                    task.setName(newTask.getName());
                    task.setStatus(newTask.getStatus());
                    return taskRepo.save(task);
                }).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @DeleteMapping("/task/{id}")
    String deleteTask(@PathVariable Long id) {
        if (!taskRepo.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepo.deleteById(id);
        return "Task Deleted";
    }
}
