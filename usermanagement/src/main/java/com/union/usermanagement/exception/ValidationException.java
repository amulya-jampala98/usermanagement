package com.union.usermanagement.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class ValidationException extends Exception {

    private Map<String, String> errorMap;

    /**
     * Exception to handle validation failures
     *
     * @param message
     * @param errorMap
     */
    public ValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
