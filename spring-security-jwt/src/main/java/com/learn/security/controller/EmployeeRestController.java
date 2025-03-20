package com.learn.security.controller;

import com.learn.security.entity.Employee;
import com.learn.security.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<String> getHomePage() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello from the Home Page of EmployeeRestController !!");
    }

    @GetMapping("/api/v1/get-all-employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @GetMapping("/api/v1/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
    }

    @GetMapping("/home/normal")
    public ResponseEntity<String> normalUserEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello I am normal User !!");
    }

    @GetMapping("/home/admin")
    public ResponseEntity<String> adminUserEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello I am Admin User !!");
    }

    @GetMapping("/home/public")
    public ResponseEntity<String> publicUserEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello I am public User !!");
    }

    @GetMapping("/home/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> customerUserEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello I am customer User !!");
    }
}
