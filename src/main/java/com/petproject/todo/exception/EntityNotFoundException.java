package com.petproject.todo.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Exception e) {
        super("Entity not found. "+e.getMessage());
    }
}
