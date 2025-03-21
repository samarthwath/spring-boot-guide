package com.learn.security.controller;

import com.learn.security.entity.User;
import com.learn.security.request.LoginRequest;
import com.learn.security.request.SignupRequest;
import com.learn.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        signupRequest.setPassword(encodedPassword);
        User savedUser = userService.registerUser(signupRequest);
        if (savedUser != null) {
            return ResponseEntity.ok().body("User Registered successfully !!!");
        }
        return ResponseEntity.unprocessableEntity().body("Error while registering user.");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> sigin(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.verify(loginRequest));
    }

    @GetMapping("/debug-auth")
    public ResponseEntity<?> debugAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth);
    }

}
