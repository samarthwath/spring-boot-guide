package com.learn.auth.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @NotBlank(message = "Username field cannot be blank.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    private String username;
    @NotBlank(message = "Password field cannot be blank.")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters.")
    private String password;

    public LoginRequest() {
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
