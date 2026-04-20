package com.revise.designpatterns.behavioral.observer;

public class TestObserverDesignPattern {

    public static void main(String[] args) {
        Subscriber subscriberFirst = new Subscriber("samarth");
        Subscriber subscriberSecond = new Subscriber("pushpak");
        Subscriber subscriberThird = new Subscriber("david");

        YoutubeChannel youtubeChannel = new YoutubeChannel("Lallantop");
        youtubeChannel.registerObserver(subscriberFirst);
        youtubeChannel.registerObserver(subscriberThird);
        youtubeChannel.registerObserver(subscriberSecond);

        youtubeChannel.updateMessage(" Uploaded new video stream live now......");

        //youtubeChannel.notifyObservers();

    }
}
