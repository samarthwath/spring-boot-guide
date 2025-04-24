package com.business.service.employee_service.exception.handler;

import com.business.service.employee_service.exception.EmployeeNotFoundException;
import com.business.service.employee_service.model.ErrorMessage;
import com.fasterxml.jackson.core.ErrorReportConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessage> employeeNotFound(EmployeeNotFoundException employeeNotFoundException){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setErrorMessage(employeeNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> genericException(Exception exception){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorMessage.setErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(errorMessage);
    }
}
