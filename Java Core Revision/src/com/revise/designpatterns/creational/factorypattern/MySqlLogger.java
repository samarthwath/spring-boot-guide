package com.revise.designpatterns.creational.factorypattern;

public class MySqlLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("MySqlLogger: " + message);
    }
}
