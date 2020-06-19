package com.example.model.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApplicationException {

    public BadRequestException(String code, Object... arguments) {
        super(HttpStatus.BAD_REQUEST, code, arguments);
    }
}
