package com.revise.designpatterns.behavioral.strategy;

public class NotificationServiceWithoutStrategy {

    public void sendMessage(String type, String message) {
        if (type.equals("EMAIL")) {
            System.out.println("Email Notification: " + message);
        } else if (type.equals("PUSH")) {
            System.out.println("Push Notification: " + message);
        } else if (type.equals("SMS")) {
            System.out.println("SMS Notification: " + message);
        }
    }
}
