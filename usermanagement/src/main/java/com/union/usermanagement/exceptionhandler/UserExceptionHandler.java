package com.union.usermanagement.exceptionhandler;

import com.union.usermanagement.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {

    /**
     * Method to handle validation exception
     *
     * @param ex
     * @return Map<String, String>
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>(ex.getErrorMap(), HttpStatus.BAD_REQUEST);
    }
}
