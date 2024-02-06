package com.petproject.todo.controller;

import com.petproject.todo.exception.EntityIllegalArgumentsException;
import com.petproject.todo.exception.EntityNotFoundException;
import com.petproject.todo.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getName()),
                HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityIllegalArgumentsException.class)
    public ResponseEntity<ErrorMessage> resourceCanNotBeCreated(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getName()),
                HttpStatus.BAD_REQUEST);
    }
}
