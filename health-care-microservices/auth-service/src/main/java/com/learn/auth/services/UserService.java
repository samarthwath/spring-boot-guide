package com.learn.auth.services;

import com.learn.auth.config.services.JwtService;
import com.learn.auth.config.services.UserPrincipal;
import com.learn.auth.entity.User;
import com.learn.auth.payload.request.LoginRequest;
import com.learn.auth.payload.request.SignupRequest;
import com.learn.auth.payload.request.payload.response.ResponseMessage;
import com.learn.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User registerUser(SignupRequest signupRequest) {
        logger.info("----- registerUser service start: -----");
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setRole(signupRequest.getRole());
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(encodedPassword);
        logger.info("----- registerUser service ends: -----");
        return userRepository.save(user);
    }


    public String verifyUser(LoginRequest loginRequest) {
        logger.info("----- verifyUser service start: -----");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginRequest.getUsername());
        }
        return null;
    }

    public boolean isTokenValid(String token) {
        logger.info("----- isTokenValid: -----");
        return jwtService.validateToken(token);
    }
}
