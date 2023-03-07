package com.todoapp.demo.exceptions;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ToDoExceptionResponse {

    private String message;
    private LocalDateTime localDateTime;

    public ToDoExceptionResponse(String message, LocalDateTime localDateTime){
        this.message = message;
        this.localDateTime = localDateTime;
    }
}
