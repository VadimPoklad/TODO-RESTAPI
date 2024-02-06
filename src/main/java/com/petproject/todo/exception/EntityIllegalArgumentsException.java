package com.petproject.todo.exception;

public class EntityIllegalArgumentsException extends RuntimeException {
    public EntityIllegalArgumentsException(Exception e) {
        super("Could not create an entity. "+e.getMessage());
    }

}
