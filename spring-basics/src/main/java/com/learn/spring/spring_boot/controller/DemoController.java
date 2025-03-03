package com.learn.spring.spring_boot.controller;

import com.learn.spring.spring_boot.controller.props.PropertyVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class DemoController {
    @Autowired
    private PropertyVariables propertyVariables;

    @GetMapping("/currency-service")
    public ResponseEntity<String> getSystemPropertyVariables() {
        return ResponseEntity.status(HttpStatus.OK).body(propertyVariables.toString());
    }
}
