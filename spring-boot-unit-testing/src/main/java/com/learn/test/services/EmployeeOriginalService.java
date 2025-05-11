package com.learn.test.services;

import com.learn.test.entity.Employee;
import com.learn.test.repository.EmployeeOriginalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeOriginalService {
    @Autowired
    private EmployeeOriginalRepository employeeOriginalRepository;

    //Service method to save the employee.
    public Employee saveEmployee(Employee employee) {
        return employeeOriginalRepository.save(employee);
    }

    //Service method to get the employee by id.
    public Optional<Employee> getEmployeeById(int id) {
        return employeeOriginalRepository.findById(id);
    }

    //Service method to get all the employees.
    public List<Employee> getAllEmployees() {
        return employeeOriginalRepository.findAll();
    }

    //Service method to delete employee by id.
    public void deleteEmployeeById(int id) {
        Optional<Employee> employeeById = employeeOriginalRepository.findById(id);
        Employee employee = employeeById.orElseThrow();
        employeeOriginalRepository.delete(employee);
    }
}
