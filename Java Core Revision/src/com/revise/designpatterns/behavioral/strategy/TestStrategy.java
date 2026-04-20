package com.revise.designpatterns.behavioral.strategy;

public class TestStrategy {
    public static void main(String[] args) {

        //Move behaviors (methods) into separate classes and let objects use them instead of implementing them directly.

        /*Every new notification type requires modifying the class

        Too many if-else

        Hard to test

        Violates Open Closed Principle
        */


       /* Now the behavior is pluggable.

        NotificationService + EmailNotification
        NotificationService + SMSNotification
        NotificationService + PushNotification

        The service stays the same, only the strategy changes.*/

        NotificationStrategy notificationStrategy = new EmailNotification();
        NotificationService notificationService = new NotificationService(notificationStrategy);
        notificationService.sendNotification("Welcome User");
    }

}
