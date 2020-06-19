package com.example.model.exception;

import com.example.utils.CollectionUtils;
import com.example.utils.MapUtils;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplicationException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final List<ErrorMessage> errorMessages = CollectionUtils.createArrayList();

    public ApplicationException(Exception exception) {
        super(exception);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApplicationException(String messageCode, Object... arguments) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, messageCode, arguments);
    }

    public ApplicationException(HttpStatus httpStatus, String messageCode, Object... arguments) {
        this.httpStatus = httpStatus;
        Map<String, Object> parameters = MapUtils.createHashMap();
        for (int index = 0; index < arguments.length - 1; index += 2) {
            parameters.put((String) arguments[index], arguments[index + 1]);
        }
        errorMessages.add(new ErrorMessage(messageCode, parameters));
    }

    public ApplicationException(HttpStatus httpStatus, Set<ConstraintViolation<Object>> violations) {
        this.httpStatus = httpStatus;
        for (ConstraintViolation<?> violation : violations) {
            errorMessages.add(new ErrorMessage(violation.getMessage()));
        }
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
