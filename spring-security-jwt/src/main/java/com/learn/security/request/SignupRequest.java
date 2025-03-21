package com.learn.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {

    @NotBlank(message = "Username field cannot be blank.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")

    private String username;

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

    @NotBlank(message = "Password field cannot be blank.")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters.")
    private String password;

    @NotBlank(message = "Roles cannot be set to blank.")
    @Size(min = 3, max = 20, message = "Roles must be between 3 and 20 characters.")
    private String roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @NotBlank(message = "Email field cannot be blank.")
    @Email(message = "Please provide valid email address.")
    private String email;


}
