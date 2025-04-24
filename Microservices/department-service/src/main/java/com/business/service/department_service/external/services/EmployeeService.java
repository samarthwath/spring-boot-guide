package com.business.service.department_service.external.services;

import com.business.service.department_service.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface EmployeeService {

    @GetMapping("/api/v1/employees/get-by-department/{id}")
    public ResponseEntity getEmployeesByDepartment(@PathVariable int id);

    @GetMapping("/api/v1/employees/find-by-department/{id}")
    public List<Employee> findEmployeesByDepartment(@PathVariable int id);
}

