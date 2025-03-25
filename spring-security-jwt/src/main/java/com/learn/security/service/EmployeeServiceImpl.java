package com.learn.security.service;

import com.learn.security.entity.Employee;
import com.learn.security.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByNameAndEmail(String name, String email) {
        List<Employee> employeeInfo = employeeRepository.findByNameAndEmail(name, email);
        return employeeInfo;
    }

    @Override
    public List<Employee> findByNameContaining(String keyword) {
        return employeeRepository.findByNameContaining(keyword);
    }

    @Override
    public List<Employee> findByAgeGreaterThan(int age) {
        return employeeRepository.findByAgeGreaterThan(age);
    }

    @Override
    public List<Employee> findByNameStartsWith(String wildCard) {
        return employeeRepository.findByNameStartingWith(wildCard);
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public List<String> findAllNames() {
        return employeeRepository.findAllNamesUsingJpql();
    }

    @Override
    public List<String> findAllNamesWithEmail() {
        return employeeRepository.findAllNamesWithEmailUsingJpql();
    }

    @Override
    public List<Employee> findByNameUsingJpql(String name) {
        return employeeRepository.findByNameUsingJpql(name);
    }

    @Override
    public List<Employee> findByNameAndEmailUsingJpql(String name, String email) {
        return employeeRepository.findByNameAndEmailUsingJpql(name, email);
    }

    @Override
    public List<String> findAllEmployeeNamesUsingNativeQuery() {
        return employeeRepository.findAllNamesUsingNativeQuery();
    }

    @Override
    public List<Employee> findByNameUsingNativeQuery(String name) {
        return employeeRepository.findByNameUsingNativeQuery(name);
    }

}
