package com.my.spring.security.controller;

import com.my.spring.security.dto.EmployeeDto;
import com.my.spring.security.model.Employee;
import com.my.spring.security.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @GetMapping("help-desk")
    public ResponseEntity<?> employeeHelpDesk() {
        return ResponseEntity.ok(List.of("ECC", "Self-Help", "ASHII"));
    }



    /*@PostMapping("signin")
    public ResponseEntity<?> signIn(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }*/

}
