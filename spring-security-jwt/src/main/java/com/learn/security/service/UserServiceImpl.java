package com.learn.security.service;

import com.learn.security.entity.User;
import com.learn.security.jwt.JwtService;
import com.learn.security.repository.UserRepository;
import com.learn.security.request.LoginRequest;
import com.learn.security.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public User registerUser(SignupRequest singupRequest) {
        User user = new User();
        user.setUsername(singupRequest.getUsername());
        user.setPassword(singupRequest.getPassword());
        user.setEmail(singupRequest.getEmail());
        user.setRoles(singupRequest.getRoles());
        User savedUserDetails = userRepository.save(user);
        return savedUserDetails;
    }

    @Override
    public String verify(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginRequest.getUsername());
        }
        return "";
    }
}
