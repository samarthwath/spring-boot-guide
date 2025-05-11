package com.learn.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.test.controller.EmployeeOriginalController;
import com.learn.test.entity.Employee;
import com.learn.test.services.EmployeeOriginalService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import com.learn.test.repository.EmployeeOriginalRepository;
import com.learn.test.services.EmployeeOriginalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeOriginalServiceTest {

    @Mock
    private EmployeeOriginalRepository employeeOriginalRepository;

    @InjectMocks
    private EmployeeOriginalService employeeOriginalService;

    @Test
    public void serviceTestSaveEmployee() {
        Employee employee = new Employee(1, "Demo", "demo@yopmial.com");
        given(employeeOriginalRepository.save(any(Employee.class))).willReturn(employee);
        Employee savedEmployee = employeeOriginalService.saveEmployee(employee);
        assertEquals(savedEmployee, employee);
        verify(employeeOriginalRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testServiceGetAllEmployees() {
        List<Employee> employeeList = List.of(new Employee(1, "Demo", "demo@yopmail.com"),
                new Employee(2, "DemoSecond", "demo@zopmail.com"),
                new Employee(3, "DemoThird", "demo@popmail.com"),
                new Employee(4, "DemoFourth", "demo@lopmail.com"));
        given(employeeOriginalRepository.findAll()).willReturn(employeeList);
        List<Employee> allEmployees = employeeOriginalService.getAllEmployees();
        assertEquals(allEmployees, employeeList);
        verify(employeeOriginalRepository, times(1)).findAll();
    }

    @Test
    public void testServiceGetEmployeeById() {
        Employee employee = new Employee(1, "DemoTest", "demo@systango.com");
        given(employeeOriginalRepository.findById(1)).willReturn(Optional.of(employee));
        Optional<Employee> employeeById = employeeOriginalService.getEmployeeById(1);
        Employee employeeFromDb = employeeById.get();
        assertEquals(employeeFromDb, employee);
        verify(employeeOriginalRepository, times(1)).findById(1);
    }

    @Test
    public void testServiceDeleteEmployeeById() {
        Employee employee = new Employee(1, "Demo Wath", "demowath@systango.com");
        given(employeeOriginalRepository.findById(1)).willReturn(Optional.of(employee));
        willDoNothing().given(employeeOriginalRepository).delete(employee);
        employeeOriginalService.deleteEmployeeById(1);
        verify(employeeOriginalRepository, times(1)).delete(employee);
    }
}
