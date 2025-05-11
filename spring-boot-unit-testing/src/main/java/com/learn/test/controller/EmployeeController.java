package com.learn.test.controller;

import com.learn.test.request.RegisterEmployee;
import com.learn.test.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("static-name")
    public ResponseEntity<String> getEmployeeName() {
        return ResponseEntity.ok(employeeService.getStaticName());
    }

    @GetMapping("greetings")
    public ResponseEntity<String> greetings() {
        return ResponseEntity.ok(employeeService.greetingsFromRepository());
    }

    @PostMapping("save-employee")
    public ResponseEntity<String> saveEmployee(@RequestBody RegisterEmployee registerEmployee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(registerEmployee));
    }
}
