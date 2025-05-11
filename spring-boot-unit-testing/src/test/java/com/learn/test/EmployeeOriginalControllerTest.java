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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@WebMvcTest(EmployeeOriginalController.class)
public class EmployeeOriginalControllerTest {

    @MockBean
    private EmployeeOriginalService employeeOriginalService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSaveEmployee() throws Exception {
        //Arrange
        Employee employee = new Employee(1, "Demo", "demo@yopmail.com");
        given(employeeOriginalService.saveEmployee(any(Employee.class))).willReturn(employee);
        mockMvc.perform(post("/api/v2/employees/save-employee")
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Demo"))
                .andExpect(jsonPath("$.email").value("demo@yopmail.com"));
        //2nd way is more helpful as it might be used in real-world project. As we deal mainly with json string.
        mockMvc.perform(post("/api/v2/employees/save-employee")
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(employee)));
        verify(employeeOriginalService, times(2)).saveEmployee(any(Employee.class));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee(2, "Demo", "demotest@yopmail.com");
        given(employeeOriginalService.getEmployeeById(2)).willReturn(Optional.of(employee));
        mockMvc.perform(get("/api/v2/employees/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Demo"))
                .andExpect(jsonPath("$.email").value("demotest@yopmail.com"));
        verify(employeeOriginalService, times(1)).getEmployeeById(any(Integer.class));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> employeeList = List.of(new Employee(1, "Demo", "demo@yopmail.com"),
                new Employee(2, "DemoSecond", "demo@zopmail.com"),
                new Employee(3, "DemoThird", "demo@popmail.com"),
                new Employee(4, "DemoFourth", "demo@lopmail.com"));
        given(employeeOriginalService.getAllEmployees()).willReturn(employeeList);
        String employeeJsonList = objectMapper.writeValueAsString(employeeList);
        mockMvc.perform(get("/api/v2/employees/get-all"))
                .andExpect(status().isOk())
                .andExpect(content().json(employeeJsonList));
        verify(employeeOriginalService, times(1)).getAllEmployees();
    }

    @Test
    public void testDeleteEmployeeById() throws Exception {
        willDoNothing().given(employeeOriginalService).deleteEmployeeById(100);
        mockMvc.perform(delete("/api/v2/employees/100"))
                .andExpect(status().isOk());
        verify(employeeOriginalService, times(1)).deleteEmployeeById(any(Integer.class));
    }
}
