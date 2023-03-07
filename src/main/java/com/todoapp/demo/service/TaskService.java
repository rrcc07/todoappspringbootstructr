package com.todoapp.demo.service;

import com.todoapp.demo.exceptions.NoSuchElementException;
import com.todoapp.demo.exceptions.NotFoundException;
import com.todoapp.demo.mapper.TaskInDTOToTask;
import com.todoapp.demo.model.entity.Task;
import com.todoapp.demo.model.entity.TaskStatus;
import com.todoapp.demo.repository.ITaskRepository;
import com.todoapp.demo.dto.TaskDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements  ITaskService{

    private final ITaskRepository repository;
    private final TaskInDTOToTask taskInDTOToTask;

    public TaskService(ITaskRepository repository, TaskInDTOToTask taskInDTOToTask) {
        this.repository = repository;
        this.taskInDTOToTask = taskInDTOToTask;
    }

    @Override
    public Optional<Task> get(Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        if(optionalTask.isEmpty()){
            throw new NoSuchElementException("no content task");
        }
        return repository.findById(id);
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
            throw new NotFoundException("Task not Found");
        }
        this.repository.markTaskAsFinished(id);
    }

    public void deleteById(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()){
            throw new NotFoundException("Task not Found");
        }
        this.repository.deleteById(id);
    }
    @Transactional
    public void changeTaskAsStatus(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new NotFoundException("Task not Found");
        }
        this.repository.markTaskStatusAsOnLate(id, String.valueOf(TaskStatus.LATE));
    }
}
