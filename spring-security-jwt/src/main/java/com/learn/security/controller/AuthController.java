package com.learn.security.controller;

import com.learn.security.entity.User;
import com.learn.security.request.LoginRequest;
import com.learn.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);
        User savedUser = userService.registerUser(userRequest);
        if (savedUser != null) {
            return ResponseEntity.ok().body("User Registered successfully !!!");
        }
        return ResponseEntity.unprocessableEntity().body("Error while registering user.");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> sigin(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.verify(loginRequest));
    }

}
