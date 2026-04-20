package com.revise.testconcepts;

public class Employee implements Comparable<Employee>{
    private String name;
    private int salary;
    private String department;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public Employee(String name, int salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}
