package com.revise.designpatterns.structural.decorator;

public class TestDecorator {
    public static void main(String[] args) {
        Coffee coffee = new BasicCoffee();
        coffee = new MilkCoffeeDecorator(coffee);
        coffee = new SugarCoffeeDecorator(coffee);
        System.out.println("Log object values: ");

        System.out.println(coffee.getDescription());
        System.out.println(coffee.getPrice());
    }


}
