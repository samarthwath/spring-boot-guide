package com.learn.doctor.utils;

import com.learn.doctor.exceptions.DoctorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ErrorMessage> patientNotFoundException(DoctorNotFoundException doctorNotFoundException) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), List.of(doctorNotFoundException.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> validationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errors = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().map(errorMessage -> errorMessage.getDefaultMessage()).collect(Collectors.toList());
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setErrors(errors);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> genericExceptionHandler(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), List.of(exception.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(errorMessage);
    }

}
