package com.todoapp.demo.service;

import com.todoapp.demo.dto.TaskDto;
import com.todoapp.demo.model.entity.Task;
import com.todoapp.demo.model.entity.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface ITaskService {

    public Optional<Task> get(Long id);
    public Task createTask(TaskDto taskDto);
    public List<Task> findAll();
    public List<Task> findAllByTaskStatus(TaskStatus taskStatus);
    public void updateTaskAsFinished(Long id);
    public void deleteById(Long id);
    public void changeTaskAsStatus(Long id);
}
