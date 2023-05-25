package com.todoapp.demo.service;

import com.todoapp.demo.dto.TaskDto;
import com.todoapp.demo.model.entity.Task;
import com.todoapp.demo.model.entity.TaskStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ITaskService<Type,TypeNew> {

    Optional<Type> get(long id);
    Collection<Type> getAll();
    Type create(TypeNew typeNew);
    boolean delete(long id);
    boolean exist(long id);

    List<Type> findAllByTaskStatus(TaskStatus taskStatus);
    void markTaskStatusAsOnLate(Long id);
    void updateTaskAsFinished(Long id);
}
