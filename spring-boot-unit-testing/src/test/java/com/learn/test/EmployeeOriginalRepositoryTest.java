package com.learn.test;

import com.learn.test.entity.Employee;
import com.learn.test.repository.EmployeeOriginalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeOriginalRepositoryTest {

    @Autowired
    private EmployeeOriginalRepository employeeOriginalRepository;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee(1, "Demo", "demo@yopmail.com");
        Employee savedEmployee = employeeOriginalRepository.save(employee);
        Employee employeeByName = employeeOriginalRepository.findEmployeeByName(employee.getName());
        Employee employeeByEmail = employeeOriginalRepository.findEmployeeByEmail(employee.getEmail());
        assertThat(employeeByName).isNotNull();
        assertThat(employeeByEmail).isNotNull();
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo(employee.getName());
    }

}
