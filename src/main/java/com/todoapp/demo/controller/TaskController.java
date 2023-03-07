package com.todoapp.demo.controller;

import com.todoapp.demo.model.entity.Task;
import com.todoapp.demo.model.entity.TaskStatus;
import com.todoapp.demo.service.ITaskService;
import com.todoapp.demo.service.TaskService;
import com.todoapp.demo.dto.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id){
        Optional<Task> optional = this.taskService.get(id);
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskDto) {
        this.taskService.createTask(taskDto);
        return ResponseEntity.created(URI.create("/tasks/" + taskDto.getTitle())).build();
    }

    @GetMapping
    public List<Task> findAll(){
        return this.taskService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByStatus(@PathVariable("status")TaskStatus status) {
        return this.taskService.findAllByTaskStatus(status);
    }

    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("mark_as_onlate/{id}")
    public ResponseEntity<Void> markAsOnLate(@PathVariable("id") Long id){
        this.taskService.changeTaskAsStatus(id);
        return ResponseEntity.noContent().build();
    }
}
