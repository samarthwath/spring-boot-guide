package com.learn.security.config.service;


public interface PaymentService {
    void setPaymentAmount(int amount);

    int getPaymentAmount();

    boolean isPaymentCompleted();
}
