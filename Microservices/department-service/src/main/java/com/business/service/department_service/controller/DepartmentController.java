package com.business.service.department_service.controller;

import com.business.service.department_service.external.services.EmployeeService;
import com.business.service.department_service.model.Department;
import com.business.service.department_service.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {

    private static final Logger logger= LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity getDepartmentById(@PathVariable int id){
        logger.info("----- getDepartmentById id: {}-----",id);
        return ResponseEntity.ok(departmentRepository.findById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllDepartments(){
        logger.info("----- getAllDepartments -----");
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @PostMapping("/add-department")
    public ResponseEntity addDepartment(@RequestBody Department department){
        logger.info("----- addDepartment department: {}-----",department);
        return ResponseEntity.ok(departmentRepository.addDepartment(department));
    }
}
