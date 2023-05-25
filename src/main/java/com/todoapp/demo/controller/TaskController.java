package com.todoapp.demo.controller;

import com.todoapp.demo.dto.NewTaskDto;
import com.todoapp.demo.exceptions.NotFoundException;
import com.todoapp.demo.mapper.TaskDTOMapper;
import com.todoapp.demo.model.entity.TaskStatus;
import com.todoapp.demo.service.ITaskService;
import com.todoapp.demo.dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    ITaskService<TaskDto, NewTaskDto> taskService;

    public TaskController(ITaskService<TaskDto, NewTaskDto> taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Collection<TaskDto>> getAll(){
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> get(@PathVariable("id") long id){
        return taskService.get(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody NewTaskDto newTaskDto) {
        TaskDto taskDto = taskService.create(newTaskDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(taskDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        if(taskService.exist(id)){
            taskService.delete(id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/status/{status}")
    public List<TaskDto> findAllByStatus(@PathVariable("status") TaskStatus status) {
        return this.taskService.findAllByTaskStatus(status);
    }

    @PatchMapping("mark_as_onlate/{id}")
    public ResponseEntity<Void> markTaskStatusAsOnLate(@PathVariable("id") Long id){
        if (!taskService.exist(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        taskService.markTaskStatusAsOnLate(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {
        if (!taskService.exist(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.ok().build();
    }


}
