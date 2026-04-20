package com.revise.designpatterns.behavioral.strategy;

public class EmailNotification implements NotificationStrategy {

    @Override
    public void sendMessage(String message) {
        System.out.println("Email: " + message);
    }
}
