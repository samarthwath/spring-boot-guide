package com.learn.security.controller;

import com.learn.security.entity.User;
import com.learn.security.request.SignupRequest;
import com.learn.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register-user")
    public ResponseEntity<User> register(@RequestBody SignupRequest signupRequest) {
        User savedUser = userService.registerUser(signupRequest);
        return ResponseEntity.ok(savedUser);
    }
}
