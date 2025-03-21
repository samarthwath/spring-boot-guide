package com.learn.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fieldErrors =
                methodArgumentNotValidException.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            String errorField = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            errorMap.put(errorField, errorMessage);
        }
        return ResponseEntity.badRequest().body(errorMap);
    }
}
