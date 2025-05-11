package com.learn.test.repository;

import com.learn.test.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeOriginalRepository extends JpaRepository<Employee, Integer> {

    Employee findEmployeeByName(String name);

    Employee findEmployeeByEmail(String email);
}
