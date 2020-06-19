package com.example.model.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApplicationException {

    public ForbiddenException(String messageCode, Object... arguments) {
        super(HttpStatus.FORBIDDEN, messageCode, arguments);
    }
}
