package com.business.service.employee_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
    public int getId() {
        return id;
    }

    public Employee() {
    }

    public Employee(int id, int departmentId, String name) {
        this.id = id;
        this.departmentId = departmentId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", departmentId=" + departmentId +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "department_id")
    private int departmentId;
    @Column(name="name")
    private String name;

}
