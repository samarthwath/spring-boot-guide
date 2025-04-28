package com.learn.doctor.utils;

import java.util.List;

public class ErrorMessage {
    private int status;
    private List<String> errors;

    public ErrorMessage() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ErrorMessage(int status, List<String> errors) {
        this.errors = errors;
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
