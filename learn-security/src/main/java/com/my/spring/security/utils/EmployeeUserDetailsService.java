package com.my.spring.security.utils;

import com.my.spring.security.model.Employee;
import com.my.spring.security.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employeeByUsername = employeeRepository.findByUsername(username);
        return employeeByUsername.map(employee -> User
                .builder()
                .username(employee.getUsername())
                .password(employee.getPassword())
                .build()).orElse(null);
    }
}
