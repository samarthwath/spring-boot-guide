package com.learn.patient.payload.request;

import jakarta.validation.constraints.*;

public class PatientRequest {
    @NotEmpty(message = "First name field is empty.")
    @Size(max = 20, min = 1, message = "Please enter valid first name.")
    private String firstName;
    @NotEmpty(message = "Last name field is empty.")
    @Size(max = 20, min = 1, message = "Please enter valid last name.")
    private String lastName;
    @NotEmpty(message = "Age field is empty.")
    private int age;
    @NotEmpty(message = "Email field is empty.")
    @Email(message = "Please enter valid email address.")
    private String email;
    @NotEmpty(message = "Phone Number field is empty.")
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public PatientRequest() {
    }

    public PatientRequest(String firstName, String lastName, int age, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
