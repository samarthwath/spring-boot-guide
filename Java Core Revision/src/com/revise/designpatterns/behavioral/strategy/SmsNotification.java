package com.revise.designpatterns.behavioral.strategy;

public class SmsNotification implements NotificationStrategy {

    @Override
    public void sendMessage(String message) {
        System.out.println("SMS: " + message);
    }
}
