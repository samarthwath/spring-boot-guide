package com.learn.test;

import com.learn.test.repository.EmployeeRepository;
import com.learn.test.request.RegisterEmployee;
import com.learn.test.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testEmployeeRepositoryGreetingsMethod() {
        given(employeeRepository.greetMessage()).willReturn("Hello World !!");
        String greetings = employeeService.greetingsFromRepository();
        assertEquals("Hello World !!", greetings);
        verify(employeeRepository, times(1)).greetMessage();
    }

    @Test
    public void testEmployeeRepositoryStaticNameMethod() {
        given(employeeRepository.getStaticName()).willReturn("Hello Samarth !!");
        assertEquals("Hello Samarth !!", employeeService.getStaticName());
        verify(employeeRepository, times(1)).getStaticName();
    }

    @Test
    public void testSaveEmployeeMethodCall() {
        given(employeeRepository.saveEmployee(any(RegisterEmployee.class))).willReturn("Employee saved.");
        RegisterEmployee registerEmployee = new RegisterEmployee(1, "Samarth Wath", "Engineer");
        String savedResult = employeeService.saveEmployee(registerEmployee);
        assertEquals("Employee saved.", savedResult);
        verify(employeeRepository, times(1)).saveEmployee(any(RegisterEmployee.class));
    }

    @Test
    public void testSaveEmployeeMethodCallWithNullEmployee() {
        given(employeeRepository.saveEmployee(null)).willReturn("Employee not saved.");
        String employeeNotSaved = employeeService.saveEmployee(null);
        assertEquals("Employee not saved.", employeeNotSaved);
        verify(employeeRepository, times(1)).saveEmployee(null);
    }

}
