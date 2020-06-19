package com.example.controller;

import com.example.model.api.ErrorResponse;
import com.example.model.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception), exception.getHttpStatus());
    }

    @ExceptionHandler({HttpMessageConversionException.class, MethodArgumentNotValidException.class, ServletException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(HttpMessageConversionException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}