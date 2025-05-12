package com.learn.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.test.controller.EmployeeOriginalController;
import com.learn.test.entity.Employee;
import com.learn.test.services.EmployeeOriginalService;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
//    @Transactional
    //Transactional annotation after execution of the test case will remove the newly created entry. As the goal is to maintain the
    //original state of the table. So it depends which one to choose.
    public void testSaveEmployeeIntegration() throws Exception {
        Employee employee = new Employee("DemoDsuza", "demodsuza@gmail.com");
        mockMvc.perform(post("/api/v2/employees/save-employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.email").value(employee.getEmail()))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testGetEmployeeByIdIntegration() throws Exception {
        Employee employee = new Employee(2, "Pushpak Test ", "pt@gmail.com");
        mockMvc.perform(get("/api/v2/employees/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employee)));
    }

    @Test
    public void testGetAllEmployeesIntegration() throws Exception {
        List<Employee> employeeList = List.of(new Employee(8, "DemoDsuza", "demodsuza@gmail.com"),
                new Employee(2, "Pushpak Test ", "pt@gmail.com"),
                new Employee(6, "Test user", "tu@yopmail.com"));
        mockMvc.perform(get("/api/v2/employees/get-all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employeeList)));
    }

//    @Test
//    public void testDeleteEmployeeByIdIntegration() throws Exception {
//        mockMvc.perform(delete("/api/v2/employees/1"))
//                .andExpect(status().isOk());
//    }
}
