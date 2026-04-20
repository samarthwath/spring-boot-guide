package com.revise.designpatterns.creational.abstractfactory;

public class WebDeveloperFactory extends EmployeeAbstractFactory {
    @Override
    public Employee createEmployee() {
        return new WebDeveloper();
    }
}
