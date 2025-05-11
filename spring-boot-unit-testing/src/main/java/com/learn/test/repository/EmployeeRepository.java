package com.learn.test.repository;

import com.learn.test.request.RegisterEmployee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRepository {

    public String greetMessage() {
        return "Hello World !!";
    }

    public String getStaticName() {
        return "Hello Samarth !!";
    }

    public String saveEmployee(RegisterEmployee registerEmployee) {
        if (registerEmployee != null) {
            return "Employee saved.";
        }
        return "Employee not saved.";

    }
}
