package com.todoapp.demo.service;

import com.todoapp.demo.exceptions.ToDoExceptions;
import com.todoapp.demo.mapper.TaskInDTOToTask;
import com.todoapp.demo.persistence.entity.Task;
import com.todoapp.demo.persistence.entity.TaskStatus;
import com.todoapp.demo.persistence.repository.TaskRepository;
import com.todoapp.demo.service.dto.TaskDto;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskInDTOToTask taskInDTOToTask;

    public TaskService(TaskRepository repository, TaskInDTOToTask taskInDTOToTask) {
        this.repository = repository;
        this.taskInDTOToTask = taskInDTOToTask;
    }

    public Task createTask(TaskDto taskDto) {
        Task task = taskInDTOToTask.map(taskDto);
        return this.repository.save(task);
    }

    public List<Task> findAll() {
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus) {
        return this.repository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not Found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinished(id);
    }

    public void deleteById(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not Found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }
}
