package com.todoapp.demo.persistence;

import com.todoapp.demo.model.entity.Task;
import com.todoapp.demo.model.entity.TaskStatus;
import com.todoapp.demo.persistence.mappers.TaskDAOMapper;
import com.todoapp.demo.persistence.repository.JPATaskRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TaskRepository implements ITaskRepository<Task>{

    JPATaskRepository jpaTaskRepository;
    TaskDAOMapper taskDAOMapper;

    public TaskRepository(JPATaskRepository jpaTaskRepository, TaskDAOMapper taskDAOMapper) {
        this.jpaTaskRepository = jpaTaskRepository;
        this.taskDAOMapper = taskDAOMapper;
    }

    @Override
    public Optional<Task> get(Long id) {
        return jpaTaskRepository.findById(id).map(taskDAOMapper::toTask);
    }

    @Override
    public Task create(Task task) {
        return taskDAOMapper.toTask(jpaTaskRepository.save(taskDAOMapper.toTask(task)));
    }

    @Override
    public Collection<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        jpaTaskRepository.findAll().forEach(taskDAO -> tasks.add(taskDAOMapper.toTask(taskDAO)));
        return tasks;
    }

    @Override
    public boolean delete(long id) {
        jpaTaskRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean exist(long id) {
        return jpaTaskRepository.existsById(id);
    }

    /***IMPLEMENTACIONES EXTRAS***/

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus) {
        return jpaTaskRepository.findAllByTaskStatus(taskStatus).stream().map(taskDAO -> taskDAOMapper.toTask(taskDAO)).collect(Collectors.toList());
    }

    public void markTaskStatusAsOnLate(Long id, String status) {
        jpaTaskRepository.markTaskStatusAsOnLate(id, status);
    }

    public void markTaskAsFinished(Long id) {
        jpaTaskRepository.markTaskAsFinished(id);
    }

}
