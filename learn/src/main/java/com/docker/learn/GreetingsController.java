package com.docker.learn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/greetings")
public class GreetingsController {

    @GetMapping("welcome-user")
    public ResponseEntity<String> welcomeUser() {
        return ResponseEntity.ok("Welcome User");
    }

}
