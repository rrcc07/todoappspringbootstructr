package com.todoapp.demo.dto;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.todoapp.demo.model.entity.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NewTaskDto {
    private String title;
    private String description;
    private LocalDateTime eta;
    private TaskStatus taskStatus;
    private LocalDateTime createdDate;


    public NewTaskDto() {
        this.createdDate = LocalDate.now().atStartOfDay();
        this.taskStatus = TaskStatus.ON_TIME;
    }
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEta() {
        return eta;
    }

    public void setEta(LocalDateTime eta) {
        this.eta = eta;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
