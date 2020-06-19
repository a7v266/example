package com.example.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class StackTraceMessage {

    @JsonProperty
    private final String className;

    @JsonProperty
    private final String methodName;

    @JsonProperty
    private final String fileName;

    @JsonProperty
    private final int lineNumber;

    StackTraceMessage(StackTraceElement element) {
        className = element.getClassName();
        methodName = element.getMethodName();
        fileName = element.getFileName();
        lineNumber = element.getLineNumber();
    }
}
