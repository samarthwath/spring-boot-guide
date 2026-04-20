package com.revise.designpatterns.structural.proxy;

public class BankPaymentImpl implements BankPayment {
    @Override
    public void withdrawMoney(int amount) {
        System.out.println("Money withdraw: " + amount);
    }
}
