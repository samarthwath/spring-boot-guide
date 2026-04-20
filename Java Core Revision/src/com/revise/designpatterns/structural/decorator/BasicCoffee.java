package com.revise.designpatterns.structural.decorator;

public class BasicCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Basic Coffee";
    }

    @Override
    public double getPrice() {
        return 100;
    }
}
