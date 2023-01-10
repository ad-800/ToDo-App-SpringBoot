package com.group2.springtodoapp.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Could not find the task");
    }
}
