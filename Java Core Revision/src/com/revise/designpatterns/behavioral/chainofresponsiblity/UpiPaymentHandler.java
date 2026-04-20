package com.revise.designpatterns.behavioral.chainofresponsiblity;

public class UpiPaymentHandler extends PaymentHandler {
    @Override
    void processPayment(double amount) {
        if (amount < 20000) {
            System.out.println(amount + " processed by UPI");
        } else {
            System.out.println("Failed to " + "process UPI");
        }
    }
}
