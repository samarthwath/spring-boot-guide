package com.learn.test;

import com.learn.test.repository.EmployeeRepository;
import com.learn.test.request.RegisterEmployee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest {

    private final EmployeeRepository employeeRepository = new EmployeeRepository();

    @Test
    public void testGreetMessageMethod() {
        String actualResult = employeeRepository.greetMessage();
        assertEquals("Hello World !!", actualResult);

    }

    @Test
    public void testStaticNameMethod() {
        String staticNameResult = employeeRepository.getStaticName();
        assertEquals("Hello Samarth !!", staticNameResult);

    }

    @Test
    public void testSaveEmployeeMethod() {
        RegisterEmployee registerEmployee = new RegisterEmployee(1, "Samarth Wath", "Engineer");
        String savedResult = employeeRepository.saveEmployee(registerEmployee);
        assertEquals("Employee saved.", savedResult);

    }
}
