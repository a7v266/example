package com.example.model.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApplicationException {

    public ConflictException(String messageCode, Object... arguments) {
        super(HttpStatus.CONFLICT, messageCode, arguments);
    }
}
