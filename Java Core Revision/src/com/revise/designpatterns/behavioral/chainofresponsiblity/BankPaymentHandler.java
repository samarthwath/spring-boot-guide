package com.revise.designpatterns.behavioral.chainofresponsiblity;

public class BankPaymentHandler extends PaymentHandler {
    @Override
    void processPayment(double amount) {
        if (amount < 10000) {
            System.out.println(amount + " processed by Bank");
        } else {
            paymentHandler.processPayment(amount);
        }
    }
}
