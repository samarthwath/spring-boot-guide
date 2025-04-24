package com.business.service.employee_service.service.impl;


import com.business.service.employee_service.service.EmployeeService;
import org.springframework.stereotype.Component;

@Component
public class DevEmployee implements EmployeeService {
    @Override
    public int getEmployeeSalary() {
        return 50000;
    }

    @Override
    public String getEmployeeDepartment() {
        return "Devlopment";
    }
}
