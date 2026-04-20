package com.learn.kafka.integration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("send")
@Slf4j
public class KafkaController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("kafka")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        log.info("message: {}", message);
        kafkaTemplate.send("fruits", message);
        return ResponseEntity.ok("Message sent successfully");
    }

}
