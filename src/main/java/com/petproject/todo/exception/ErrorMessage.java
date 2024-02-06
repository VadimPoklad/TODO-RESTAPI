package com.petproject.todo.exception;

import java.time.LocalDateTime;

public record ErrorMessage(
        String massage,
        String exceptionName,
        LocalDateTime timestamp

) {
    public ErrorMessage(String exceptionName, String massage) {
        this(exceptionName, massage, LocalDateTime.now());
    }
}