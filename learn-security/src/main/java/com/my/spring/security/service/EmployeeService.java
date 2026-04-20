package com.my.spring.security.service;

import com.my.spring.security.model.Employee;
import com.my.spring.security.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee save(Employee employee) {
        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        Employee save = employeeRepository.save(employee);
        return save;
    }
}
