package com.learn.mvc.controller;

import com.learn.mvc.entity.Employee;
import com.learn.mvc.repository.EmployeeRepository;
import com.learn.mvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rest-employee")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<String> getHomePage() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello from the Home page of EmployeeRestController !!!");
    }

    @GetMapping("/getall-employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(allEmployees);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted");
    }

}
