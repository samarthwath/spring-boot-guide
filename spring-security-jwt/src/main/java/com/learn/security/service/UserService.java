package com.learn.security.service;

import com.learn.security.entity.User;
import com.learn.security.request.LoginRequest;

public interface UserService {
    User registerUser(User user);

    String verify(LoginRequest loginRequest);
}
