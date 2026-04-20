package com.revise.designpatterns.behavioral.strategy;

public class NotificationService {
    private NotificationStrategy notificationStrategy;

    public NotificationService(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void sendNotification(String message) {
        notificationStrategy.sendMessage(message);
    }

}
