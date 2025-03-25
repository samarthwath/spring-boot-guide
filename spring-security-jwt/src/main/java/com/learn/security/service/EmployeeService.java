package com.learn.security.service;

import com.learn.security.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(int id);
    void deleteEmployeeById(int id);

    List<Employee> findByNameAndEmail(String name, String email);

    List<Employee> findByNameContaining(String keyword);

    List<Employee> findByAgeGreaterThan(int age);

    List<Employee> findByNameStartsWith(String wildCard);

    List<Employee> findByName(String name);

    List<String> findAllNames();

    List<String> findAllNamesWithEmail();

    List<Employee> findByNameUsingJpql(String name);

    List<Employee> findByNameAndEmailUsingJpql(String name, String email);

    List<String> findAllEmployeeNamesUsingNativeQuery();

    List<Employee> findByNameUsingNativeQuery(String name);
}
