package com.learn.security.controller;

import com.learn.security.relax.binding.DbConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("generic")
public class GenericController {

    @Autowired
    private DbConfiguration dbConfiguration;

    @GetMapping("db-information")
    public ResponseEntity<Map<String, String>> getDbInformation() {
        Map<String, String> dbInformation = new HashMap<>();
        dbInformation.put("dbUserName", dbConfiguration.getUserName());
        dbInformation.put("dbPassword", dbConfiguration.getUserPassword());
        dbInformation.put("dbPort", dbConfiguration.getServerPort());
        dbInformation.put("dbProtocol", dbConfiguration.getServerProtocol());
        dbInformation.put("testField", dbConfiguration.getTestField());
        return ResponseEntity.ok(dbInformation);
    }
}
