package com.example.model.api;

import com.example.model.exception.ApplicationException;
import com.example.model.exception.ErrorMessage;
import com.example.utils.StringJoiner;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorResponse {

    public static final String DELIMITER = ": ";

    @JsonProperty
    private final String error;

    @JsonProperty
    private final List<StackTraceMessage> stackTrace;

    @JsonProperty
    private List<ErrorMessage> messages;

    public ErrorResponse(Exception exception) {
        error = StringJoiner.on(DELIMITER).skipEmpty().join(exception.getClass().getName(), exception.getMessage());
        stackTrace = Arrays.stream(exception.getStackTrace()).map(StackTraceMessage::new).collect(Collectors.toList());
    }

    public ErrorResponse(ApplicationException exception) {
        this((Exception) exception);
        this.messages = exception.getErrorMessages();
    }
}
