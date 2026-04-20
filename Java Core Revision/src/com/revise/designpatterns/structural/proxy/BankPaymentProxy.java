package com.revise.designpatterns.structural.proxy;

public class BankPaymentProxy implements BankPayment {
    private BankPaymentImpl bankPayment = new BankPaymentImpl();
    private String role;

    public BankPaymentProxy(String role) {
        this.role = role;
    }

    @Override
    public void withdrawMoney(int amount) {
        if ("ADMIN".equals(role)) {
            bankPayment.withdrawMoney(amount);
        } else {
            System.out.println("User is not an admin withdraw cannot happen");
        }
    }
}
