package com.example.myspringboot.service;

import com.example.myspringboot.dto.EmployeeRequest;
import com.example.myspringboot.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse getById(Long id);

    List<EmployeeResponse> getAll();

    EmployeeResponse update(Long id, EmployeeRequest request);

    void delete(Long id);
}
