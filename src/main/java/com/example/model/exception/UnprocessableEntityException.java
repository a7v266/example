package com.example.model.exception;

import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UnprocessableEntityException extends ApplicationException {

    public UnprocessableEntityException(String messageCode, Object... arguments) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, messageCode, arguments);
    }

    public UnprocessableEntityException(Set<ConstraintViolation<Object>> violations) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, violations);
    }
}
