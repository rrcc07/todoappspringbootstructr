package com.todoapp.demo.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    private String title;
    private String description;
    private LocalDateTime eta;
}
