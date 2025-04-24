package com.business.service.department_service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface EmployeeClient {
    @GetMapping("/api/v1/employees/get-by-department/{id}")
    public ResponseEntity getEmployeesByDepartment(@PathVariable int id);
}
