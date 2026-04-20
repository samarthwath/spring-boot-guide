package com.revise.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class YoutubeChannel implements Subject {
    private List<Observer> observers = new ArrayList<>();

    private String name;

    private String message;

    public YoutubeChannel(String name) {
        this.name = name;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void updateMessage(String message) {
        this.message = message;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update("Channel: " + name + message);
        }
    }
}
