package com.todoapp.demo.service;

import com.todoapp.demo.dto.NewTaskDto;
import com.todoapp.demo.exceptions.NoSuchElementException;
import com.todoapp.demo.exceptions.NotFoundException;
import com.todoapp.demo.mapper.NewTaskDTOMapper;
import com.todoapp.demo.mapper.TaskDTOMapper;
import com.todoapp.demo.mapper.TaskInDTOToTask;
import com.todoapp.demo.model.entity.Task;
import com.todoapp.demo.model.entity.TaskStatus;
import com.todoapp.demo.persistence.ITaskRepository;
import com.todoapp.demo.dto.TaskDto;
import com.todoapp.demo.persistence.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService<TaskDto, NewTaskDto>{

    TaskRepository taskRepository;
    TaskDTOMapper taskDTOMapper;
    NewTaskDTOMapper newTaskDTOMapper;

    public TaskService(TaskRepository taskRepository, TaskDTOMapper taskDTOMapper, NewTaskDTOMapper newTaskDTOMapper) {
        this.taskRepository = taskRepository;
        this.taskDTOMapper = taskDTOMapper;
        this.newTaskDTOMapper = newTaskDTOMapper;
    }

    @Override
    public Optional<TaskDto> get(long id) {
        return taskRepository.get(id).map(task -> taskDTOMapper.toTask(task));
    }

    @Override
    public Collection<TaskDto> getAll() {
        return taskRepository.getAll().stream().map(task -> taskDTOMapper.toTask(task)).collect(Collectors.toList());
    }


    @Override
    public TaskDto create(NewTaskDto newTaskDto) {
        Task task = newTaskDTOMapper.toTask(newTaskDto);
        return taskDTOMapper.toTask(taskRepository.create(task));
    }

    @Override
    public boolean delete(long id){
        return taskRepository.delete(id);
    }

    @Override
    public boolean exist(long id){
        return taskRepository.exist(id);
    }

    /***IMPLEMENTACIONES EXTRAS***/
    @Override
    public List<TaskDto> findAllByTaskStatus(TaskStatus taskStatus) {
        return taskRepository.findAllByTaskStatus(taskStatus).stream().map(task -> taskDTOMapper.toTask(task)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void markTaskStatusAsOnLate(Long id){
        taskRepository.markTaskStatusAsOnLate(id, String.valueOf(TaskStatus.LATE));
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        taskRepository.markTaskAsFinished(id);
    }
}
