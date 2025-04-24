package com.business.service.department_service.repository;

import com.business.service.department_service.external.services.EmployeeService;
import com.business.service.department_service.model.Department;
import com.business.service.department_service.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DepartmentRepository {
    private List<Department> departments = new ArrayList<>();

    @Autowired
    private EmployeeService employeeService;

    public Department addDepartment(Department department) {
        departments.add(department);
        return department;
    }

    public Department findById(int id) {
        Optional<Department> departmentDetails = departments.stream().filter(department -> department.getId() == id).findFirst();
        return departmentDetails.get();
    }

    public List<Department> findAll() {
        List<Department> updatedDepartmentList = new ArrayList<>();
        for (Department department : departments) {
            int departmentId = department.getId();
            ResponseEntity employeesByDepartment = employeeService.getEmployeesByDepartment(departmentId);
            List<Employee> employeeList = employeeService.findEmployeesByDepartment(departmentId);
            employeeList.forEach(employee -> department.getEmployees().add(employee));
            updatedDepartmentList.add(department);
        }
        return departments;
    }


}
