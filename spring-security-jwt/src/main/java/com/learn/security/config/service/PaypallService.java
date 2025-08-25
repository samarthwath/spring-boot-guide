package com.learn.security.config.service;

public class PaypallService implements PaymentService{
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
