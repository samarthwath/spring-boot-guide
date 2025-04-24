package com.business.service.employee_service.respository;

import com.business.service.employee_service.exception.EmployeeNotFoundException;
import com.business.service.employee_service.model.EmployeeDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private List<EmployeeDto> employees=new ArrayList<>();

    public EmployeeDto saveEmployee(EmployeeDto employee){
        employees.add(employee);
        return employee;
    }

    public List<EmployeeDto> getEmployees(){
        return employees;
    }

    public EmployeeDto findById(int id) throws EmployeeNotFoundException {
        Optional<EmployeeDto> employeeInfo=employees.stream().filter(employee->employee.id()==id).findFirst();
        if(employeeInfo.isPresent()){
            return employeeInfo.get();
        }else {
            throw new EmployeeNotFoundException("Employee Not Found with Id: "+id);
        }
    }

    public List<EmployeeDto> findEmployeesByDepartment(int departmentId){
        return employees.stream().filter(employee->employee.departmentId()==departmentId).collect(Collectors.toList());
    }
}
