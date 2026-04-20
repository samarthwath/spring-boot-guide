package com.revise.designpatterns.creational.abstractfactory;

public class EmployeeFactory {

    public static Employee getEmployee(EmployeeAbstractFactory employeeAbstractFactory) {
        return employeeAbstractFactory.createEmployee();
    }
}
