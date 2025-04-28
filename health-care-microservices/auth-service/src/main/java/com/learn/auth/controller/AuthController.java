package com.learn.auth.controller;

import com.learn.auth.entity.User;
import com.learn.auth.payload.request.LoginRequest;
import com.learn.auth.payload.request.SignupRequest;
import com.learn.auth.payload.request.payload.response.ResponseMessage;
import com.learn.auth.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        ResponseMessage responseMessage = new ResponseMessage();
        logger.info("----- registerUser start: -----");
        User user = userService.registerUser(signupRequest);
        if (user != null) {
            responseMessage.setStatus(HttpStatus.CREATED.value());
            responseMessage.setMessage("User Registered successfully !");
            return ResponseEntity.ok(responseMessage);
        } else {
            responseMessage.setMessage("Error while creating the user.");
            responseMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.info("Error while saving the user.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    @PostMapping("signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("----- signin : -----");
        String jsonToken = userService.verifyUser(loginRequest);
        return ResponseEntity.ok(jsonToken);
    }

    @GetMapping("validate-token")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        logger.info("----- validateToken: -----");
        return ResponseEntity.ok(userService.isTokenValid(token));
    }


}
