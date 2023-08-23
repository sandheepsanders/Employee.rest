package com.features.springbootbackend.service;

import com.features.springbootbackend.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(int id);
    Task addTask(Task task);
    Task updateTask(int id, Task updatedTask);
    void deleteTask(int id);
}

