package com.revise.designpatterns.behavioral.strategy;

public class TestWithoutStrategy {
    public static void main(String[] args) {
        NotificationServiceWithoutStrategy notificationServiceWithoutStrategy = new NotificationServiceWithoutStrategy();
        notificationServiceWithoutStrategy.sendMessage("EMAIL", "Welcome Mail");
    }
}
