package com.revise.designpatterns.structural.proxy;

public class MainClient {
    public static void main(String[] args) {
        BankPayment bankPayment = new BankPaymentProxy("ADMIN");
        bankPayment.withdrawMoney(3000);
    }

}
