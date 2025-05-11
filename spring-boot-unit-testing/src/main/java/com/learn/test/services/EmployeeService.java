package com.learn.test.services;

import com.learn.test.repository.EmployeeRepository;
import com.learn.test.request.RegisterEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String greetingsFromRepository() {
        return employeeRepository.greetMessage();
    }

    public String getStaticName() {
        return employeeRepository.getStaticName();
    }

    public String saveEmployee(RegisterEmployee registerEmployee) {
        return employeeRepository.saveEmployee(registerEmployee);
    }
}
