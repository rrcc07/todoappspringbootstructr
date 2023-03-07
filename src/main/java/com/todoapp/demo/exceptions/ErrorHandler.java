package com.todoapp.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest webRequest){
        ToDoExceptionResponse toDoExceptionResponse = new ToDoExceptionResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(toDoExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(Exception e, WebRequest webRequest){
        ToDoExceptionResponse toDoExceptionResponse = new ToDoExceptionResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(toDoExceptionResponse, HttpStatus.NO_CONTENT);
    }
}
