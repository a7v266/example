package com.example.model.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ErrorMessage {

    @JsonProperty
    private final String code;

    @JsonProperty
    private Map<String, Object> parameters;

    public ErrorMessage(String code) {
        this.code = code;
    }

    public ErrorMessage(String code, Map<String, Object> parameters) {
        this.code = code;
        this.parameters = parameters;
    }
}
