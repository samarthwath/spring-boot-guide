package com.learn.security.service;

import com.learn.security.entity.User;
import com.learn.security.request.LoginRequest;
import com.learn.security.request.SignupRequest;

public interface UserService {
    User registerUser(SignupRequest signupRequest);

    String verify(LoginRequest loginRequest);
}
