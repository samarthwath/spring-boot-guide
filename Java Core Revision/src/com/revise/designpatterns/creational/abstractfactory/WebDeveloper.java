package com.revise.designpatterns.creational.abstractfactory;

public class WebDeveloper implements Employee{
    @Override
    public int getSalary() {
        return 70000;
    }

    @Override
    public String getDepartmentName() {
        return "WEB_DEVELOPMENT_TEAM";
    }
}
