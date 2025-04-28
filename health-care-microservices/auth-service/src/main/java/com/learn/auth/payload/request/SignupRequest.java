package com.learn.auth.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotBlank(message = "Username field should not be blank.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    private String username;
    @NotBlank(message = "Email field should not be blank.")
    @Email(message = "Please provide valid email address.")
    private String email;
    @NotBlank(message = "Password field should not be blank.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    private String password;
    @NotBlank(message = "Role field should not be blank.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SignupRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignupRequest(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
