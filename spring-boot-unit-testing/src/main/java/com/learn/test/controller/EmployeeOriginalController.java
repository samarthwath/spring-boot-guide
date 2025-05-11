package com.learn.test.controller;

import com.learn.test.entity.Employee;
import com.learn.test.services.EmployeeOriginalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/employees")
public class EmployeeOriginalController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeOriginalController.class);

    @Autowired
    private EmployeeOriginalService employeeOriginalService;

    @PostMapping("save-employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        logger.info("----- saveEmployee -----");
        Employee savedEmployee = employeeOriginalService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        logger.info("----- getEmployeeById -----");
        Optional<Employee> employeeById = employeeOriginalService.getEmployeeById(id);
        Employee employee = employeeById.orElseThrow();
        return ResponseEntity.ok(employee);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("----- getAllEmployees -----");
        return ResponseEntity.ok(employeeOriginalService.getAllEmployees());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
        logger.info("----- deleteEmployeeById -----");
        employeeOriginalService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee with Id: " + id + " deleted");
    }

}
