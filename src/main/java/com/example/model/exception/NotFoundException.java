package com.example.model.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException {

    public NotFoundException(String messageCode, Object... arguments) {
        super(HttpStatus.NOT_FOUND, messageCode, arguments);
    }
}
