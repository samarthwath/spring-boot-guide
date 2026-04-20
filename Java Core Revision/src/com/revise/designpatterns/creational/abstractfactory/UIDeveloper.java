package com.revise.designpatterns.creational.abstractfactory;

public class UIDeveloper implements Employee {
    @Override
    public int getSalary() {
        return 90000;
    }

    @Override
    public String getDepartmentName() {
        return "UI_DEVELOPMENT_TEAM";
    }
}
