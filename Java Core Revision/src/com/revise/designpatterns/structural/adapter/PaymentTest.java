package com.revise.designpatterns.structural.adapter;

public class PaymentTest {
    public static void main(String[] args) {
        LegacyPaymentGateway legacyPaymentGateway = new LegacyPaymentGateway();
        PaymentProcessor paymentProcessor = new PaymentAdapter(legacyPaymentGateway);

        paymentProcessor.newPaymentProcessor();


    }

}
