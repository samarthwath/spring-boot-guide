package com.learn.kafka.integration.controller;

import com.learn.kafka.integration.service.CircuitBreakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("circuit-breaker")
public class CircuitBreakerController {

    @Autowired
    private CircuitBreakerService circuitBreakerService;

    @GetMapping("get-external-data")
    public ResponseEntity<?> getData(@RequestParam String city) throws Exception {
        return ResponseEntity.ok(circuitBreakerService.getDataFromExternalApi(city));
    }

    @GetMapping("get-external-data-retry")
    public ResponseEntity<?> getDataRetry(@RequestParam String city) throws Exception {
        return ResponseEntity.ok(circuitBreakerService.getDataFromExternalApiRetry(city));
    }

    @GetMapping("clear-counter")
    public ResponseEntity<?> clearCounter() {
        circuitBreakerService.clearCounter();
        return ResponseEntity.ok("Counter cleared");
    }

}
