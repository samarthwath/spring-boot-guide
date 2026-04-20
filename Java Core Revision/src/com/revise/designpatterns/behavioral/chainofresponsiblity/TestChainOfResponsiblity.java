package com.revise.designpatterns.behavioral.chainofresponsiblity;

public class TestChainOfResponsiblity {
    public static void main(String[] args) {
        PaymentHandler bankPaymentHandler = new BankPaymentHandler();
        PaymentHandler creditCardPaymentHandler = new CreditCardPaymentHandler();
        PaymentHandler upiPaymentHandler = new UpiPaymentHandler();

        bankPaymentHandler.setNextPaymentHandler(creditCardPaymentHandler);
        creditCardPaymentHandler.setNextPaymentHandler(upiPaymentHandler);

        bankPaymentHandler.processPayment(15000);
        bankPaymentHandler.processPayment(5000);
        bankPaymentHandler.processPayment(10000);
    }

}
