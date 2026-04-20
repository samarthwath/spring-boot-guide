package com.revise.designpatterns.behavioral.chainofresponsiblity;

public class CreditCardPaymentHandler extends PaymentHandler {
    @Override
    void processPayment(double amount) {
        if (amount < 15000) {
            System.out.println(amount + " processed by CreditCard");
        } else {
            paymentHandler.processPayment(amount);
        }
    }
}
