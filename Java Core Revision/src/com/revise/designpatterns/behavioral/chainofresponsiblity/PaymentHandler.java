package com.revise.designpatterns.behavioral.chainofresponsiblity;

public abstract class PaymentHandler {
    protected PaymentHandler paymentHandler;

    abstract void processPayment(double amount);

    public void setNextPaymentHandler(PaymentHandler paymentHandler) {
        this.paymentHandler = paymentHandler;
    }

}
