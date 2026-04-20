package com.revise.designpatterns.creational.abstractfactory;

public class AndroidDeveloper implements Employee {
    @Override
    public int getSalary() {
        return 50000;
    }

    @Override
    public String getDepartmentName() {
        return "ANDROID_DEVELOPMENT_TEAM";
    }
}
