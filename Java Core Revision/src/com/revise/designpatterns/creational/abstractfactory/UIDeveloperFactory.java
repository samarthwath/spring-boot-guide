package com.revise.designpatterns.creational.abstractfactory;

public class UIDeveloperFactory extends EmployeeAbstractFactory {
    @Override
    public Employee createEmployee() {
        return new UIDeveloper();
    }
}
