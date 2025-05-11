package com.learn.test;

import com.learn.test.controller.EmployeeController;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.BDDMockito.given;

import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.learn.test.request.RegisterEmployee;
import com.learn.test.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    /*
    The given–when–then pattern (from Behavior-Driven Development) is just a readable version of:

    Arrange → set up the test

    Act → call the method you're testing

    Assert → check the result

    */

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testEmployeeControllerGreetingsApi() throws Exception {
        given(employeeService.greetingsFromRepository()).willReturn("Hello World !!");
        mockMvc.perform(get("/api/v1/employees/greetings"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World !!"));
        verify(employeeService).greetingsFromRepository();
    }

    @Test
    public void testEmployeeControllerStaticNameApi() throws Exception {
        given(employeeService.getStaticName()).willReturn("Hello Samarth !!");
        mockMvc.perform(get("/api/v1/employees/static-name"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Samarth !!"));
        verify(employeeService).getStaticName();
    }

    @Test
    public void testSaveEmployeePostCall() throws Exception {
        RegisterEmployee registerEmployee = new RegisterEmployee(1, "Samarth Wath", "Engineering");
        given(employeeService.saveEmployee(any(RegisterEmployee.class))).willReturn("Employee saved.");
        mockMvc.perform(post("/api/v1/employees/save-employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerEmployee)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Employee saved."));
        verify(employeeService).saveEmployee(any(RegisterEmployee.class));
    }
}
