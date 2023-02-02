package com.todoapp.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ToDoExceptions.class})
    protected ResponseEntity<Object> handleConflict(ToDoExceptions e, WebRequest request) {
        String bodyOfResponse = e.getMessage();
        return handleExceptionInternal(e, bodyOfResponse, new HttpHeaders(), e.getHttpStatus(), request);
    }
}
