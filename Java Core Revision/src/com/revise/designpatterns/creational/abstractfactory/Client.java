package com.revise.designpatterns.creational.abstractfactory;

public class Client {
    public static void main(String[] args) {
        Employee employeeAndroid = EmployeeFactory.getEmployee(new AndroidDevFactory());
        System.out.println("Employee Department: " + employeeAndroid.getDepartmentName());
        System.out.println("Employee salary: " + employeeAndroid.getSalary());

        Employee employeeUI = EmployeeFactory.getEmployee(new UIDeveloperFactory());
        System.out.println("Employee Department: " + employeeUI.getDepartmentName());
        System.out.println("Employee salary: " + employeeUI.getSalary());

        Employee employeeWebDev = EmployeeFactory.getEmployee(new WebDeveloperFactory());
        System.out.println("Employee Department: " + employeeWebDev.getDepartmentName());
        System.out.println("Employee salary: " + employeeWebDev.getSalary());

    }

}
