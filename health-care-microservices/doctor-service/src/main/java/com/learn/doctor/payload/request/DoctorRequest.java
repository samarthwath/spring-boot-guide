package com.learn.doctor.payload.request;

import jakarta.validation.constraints.*;

public class DoctorRequest {
    @NotEmpty(message = "First name field is empty.")
    @Size(max = 20, min = 1, message = "Please enter valid first name.")
    private String firstName;
    @NotEmpty(message = "Last name field is empty.")
    @Size(max = 20, min = 1, message = "Please enter valid last name.")
    private String lastName;
    @NotNull(message = "Experience field is empty.")
    @Min(value = 1, message = "Experience should be of minimum 1 year.")
    private int experience;
    @NotEmpty(message = "Email field is empty.")
    @Email(message = "Please enter valid email address.")
    private String email;
    @NotEmpty(message = "Phone Number field is empty.")
    private String phone;
    @NotEmpty(message = "Speciality field is empty.")
    @Size(max = 20, min = 5, message = "Please enter valid speciality.")
    private String speciality;
    @NotEmpty(message = "Status field is empty.")
    @Size(max = 20, min = 5, message = "Please enter valid status field.")
    private String status;

    public String getFirstName() {
        return firstName;
    }

    public DoctorRequest() {
    }

    public String getSpeciality() {
        return speciality;
    }

    @Override
    public String toString() {
        return "DoctorRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", experience=" + experience +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", speciality='" + speciality + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public DoctorRequest(String firstName, String lastName, String email, String speciality, String phone, String status, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.email = email;
        this.phone = phone;
        this.speciality = speciality;
        this.status = status;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
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
