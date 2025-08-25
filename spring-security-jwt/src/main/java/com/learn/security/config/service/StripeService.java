package com.learn.security.config.service;

import org.springframework.stereotype.Component;

public class StripeService implements PaymentService{


    @Override
    public void setPaymentAmount(int amount) {

    }

    @Override
    public int getPaymentAmount() {
        return 0;
    }

    @Override
    public boolean isPaymentCompleted() {
        return false;
    }
}
