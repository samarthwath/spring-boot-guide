package com.business.service.employee_service.controller;

import com.business.service.employee_service.entity.Employee;
import com.business.service.employee_service.exception.EmployeeNotFoundException;
import com.business.service.employee_service.model.EmployeeDto;
import com.business.service.employee_service.respository.EmployeeJpaRepository;
import com.business.service.employee_service.respository.EmployeeOriginalRepository;
import com.business.service.employee_service.respository.EmployeeRepository;
import com.business.service.employee_service.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private static final Logger logger= LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeOriginalRepository employeeOriginalRepository;

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    @Qualifier("devEmployee")
    private EmployeeService employeeService;

    @PostMapping("/save-employee")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employee){
        logger.info("----- saveEmployee ----- employee {}", employee);
        //below 1st implementation is without any database call.
        //below 2nd implementation is with db call but normal jpa call.
        //below 3rd implementation is purely JpaRepository call.
//        return ResponseEntity.ok(employeeRepository.saveEmployee(employee));
//        return ResponseEntity.ok(employeeOriginalRepository.saveEmployee(employee));
        Employee employeeEntity=new Employee();
        employeeEntity.setName(employee.name());
        employeeEntity.setDepartmentId(employee.departmentId());
        employeeEntity.setId(employee.id());
        Employee savedEmployee = employeeJpaRepository.save(employeeEntity);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity findEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
        logger.info("---- findEmployeeById ----- id {}", id);
        return ResponseEntity.ok(employeeRepository.findById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllEmployees(){
        logger.info("----- getAllEmployees ----- ");
        return ResponseEntity.ok(employeeRepository.getEmployees());
    }
    @GetMapping("/get-by-department/{id}")
    public ResponseEntity getEmployeesByDepartment(@PathVariable int id){
        logger.info("----- getEmployeesByDepartment ----- id {}", id);
        return ResponseEntity.ok(employeeRepository.findEmployeesByDepartment(id));
    }

    @GetMapping("/find-by-department/{id}")
    public List<EmployeeDto> findEmployeesByDepartment(@PathVariable int id){
        logger.info("----- findEmployeesByDepartment ----- id {}", id);
        return employeeRepository.findEmployeesByDepartment(id);
    }

    @GetMapping("/dev-employee-salary")
    public int devEmployeesSalary(){
        logger.info("----- devEmployeesSalary ----- ");
        return employeeService.getEmployeeSalary();
    }

}