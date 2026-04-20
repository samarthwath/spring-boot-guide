package com.revise.designpatterns.structural.decorator;

public class MilkCoffeeDecorator extends CoffeeDecorator {


    public MilkCoffeeDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " with Milk add-on";
    }

    @Override
    public double getPrice() {
        return coffee.getPrice() + 20;
    }
}
