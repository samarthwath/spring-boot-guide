package com.business.service.employee_service.respository;

import com.business.service.employee_service.entity.Employee;
import com.business.service.employee_service.model.EmployeeDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeOriginalRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public EmployeeDto saveEmployee(EmployeeDto employee){
        Employee employeeEntity=new Employee();
        employeeEntity.setName(employee.name());
        employeeEntity.setDepartmentId(employee.departmentId());
        entityManager.persist(employeeEntity);
        return employee;
    }
}
