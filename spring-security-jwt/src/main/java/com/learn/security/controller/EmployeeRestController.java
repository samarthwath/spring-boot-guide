package com.learn.security.controller;

import com.learn.security.entity.Employee;
import com.learn.security.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("api/v1/get-employee-info")
    public ResponseEntity<List<Employee>> findEmployeeNameAndEmail(@RequestParam String name, @RequestParam String email) {
        return ResponseEntity.ok(employeeService.findByNameAndEmail(name, email));
    }

    @GetMapping("api/v1/get-employee-with-containing-char")
    public ResponseEntity<List<Employee>> findEmployeeStartingWithS(@RequestParam String keyword) {
        return ResponseEntity.ok(employeeService.findByNameContaining(keyword));
    }

    @GetMapping("api/v1/get-employees-with-age-filter")
    public ResponseEntity<List<Employee>> findByEmployeesWithValidAge(@RequestParam int age) {
        return ResponseEntity.ok(employeeService.findByAgeGreaterThan(age));
    }

    @GetMapping("api/v1/get-employee-with-name-start-filter")
    public ResponseEntity<List<Employee>> findEmployeeWithNameStartsWith(@RequestParam String wildCard) {
        return ResponseEntity.ok(employeeService.findByNameStartsWith(wildCard));
    }

    @GetMapping("api/v1/{name}/get-employee")
    public ResponseEntity<List<Employee>> findEmployeeByName(@PathVariable String name) {
        return ResponseEntity.ok(employeeService.findByName(name));
    }

    @GetMapping("api/v1/get-employee-names")
    public ResponseEntity<List<String>> findAllEmployeeNames() {
        return ResponseEntity.ok(employeeService.findAllNames());
    }

    @GetMapping("api/v1/get-employees-name-email")
    public ResponseEntity<List<String>> findAllEmployeeNamesWithEmail() {
        return ResponseEntity.ok(employeeService.findAllNamesWithEmail());
    }

    @GetMapping("api/v1/{name}/get-employee-jpql")
    public ResponseEntity<List<Employee>> findEmployeeByNameUsingJpql(@PathVariable String name) {
        return ResponseEntity.ok(employeeService.findByNameUsingJpql(name));
    }

    @GetMapping("api/v1/{name}/{email}/get-employee-jpql")
    public ResponseEntity<List<Employee>> findEmployeeByNameAndEmailUsingJpql(@PathVariable String name, @PathVariable String email) {
        return ResponseEntity.ok(employeeService.findByNameAndEmailUsingJpql(name, email));
    }

    @GetMapping("api/v1/get-employee-names-native-query")
    public ResponseEntity<List<String>> findAllEmployeeNamesWithNativeQuery() {
        return ResponseEntity.ok(employeeService.findAllEmployeeNamesUsingNativeQuery());
    }

    @GetMapping("api/v1/{name}/get-employee-native-query")
    public ResponseEntity<List<Employee>> findEmployeeByNameWithNativeQuery(@PathVariable String name) {
        return ResponseEntity.ok(employeeService.findByNameUsingNativeQuery(name));
    }
}
