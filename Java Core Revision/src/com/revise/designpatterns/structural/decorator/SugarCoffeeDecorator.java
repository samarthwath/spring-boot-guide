package com.revise.designpatterns.structural.decorator;

public class SugarCoffeeDecorator extends CoffeeDecorator {

    public SugarCoffeeDecorator(Coffee coffee) {
        super(coffee);
    }


    @Override
    public String getDescription() {
        return coffee.getDescription() + " Sugar add-on";
    }

    @Override
    public double getPrice() {
        return coffee.getPrice() + 5;
    }
}
