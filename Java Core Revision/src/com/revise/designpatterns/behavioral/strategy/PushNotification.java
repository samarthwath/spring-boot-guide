package com.revise.designpatterns.behavioral.strategy;

public class PushNotification implements NotificationStrategy {

    @Override
    public void sendMessage(String message) {
        System.out.println("PUSH: " + message);
    }
}
