package com.revise.designpatterns.structural.adapter;

public class PaymentAdapter implements PaymentProcessor {

    private LegacyPaymentGateway legacyPaymentGateway;

    public PaymentAdapter(LegacyPaymentGateway legacyPaymentGateway) {
        this.legacyPaymentGateway = legacyPaymentGateway;
    }


    @Override
    public void newPaymentProcessor() {
        System.out.println("Inside PaymentAdapter.newPaymentProcessor");
        legacyPaymentGateway.legacyProcessPayment();
    }
}
