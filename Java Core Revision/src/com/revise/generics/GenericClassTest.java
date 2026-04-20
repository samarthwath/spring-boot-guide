package com.revise.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericClassTest {
    public static void main(String[] args) {
        GenericClass<String> genericClass = new GenericClass();
        genericClass.setValue("Hello World");
        System.out.println("Log value: " + genericClass.getValue());

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);

        GenericClass<List<Integer>> genericList = new GenericClass();
        genericList.setValue(integers);
        System.out.println("Log value: " + genericList.getValue());

        GenericClassTest.print("Hey There");
        GenericClassTest.print("Hello World!!");
        GenericClassTest.print(integers);

        double sumValue = GenericClassTest.add(100, 101.5);
        GenericClassTest.print(sumValue);


        GenericInterfaceImpl genericInterfaceImpl = new GenericInterfaceImpl();
        genericInterfaceImpl.processToUpperCase("hi there samarth");
        genericInterfaceImpl.log("Hello World !!");

        GenericClassTest.printNumbersList(Arrays.asList(123, 4.5, 67, 88.12345));

        GenericClassTest.printNewNumbers(123);

    }


    public static <T> void print(T value) {
        System.out.println("Log input value: " + value);
    }

    public static <T extends Number> double add(T valueFirst, T valueSecond) {
        return valueFirst.doubleValue() + valueSecond.doubleValue();
    }

    public static void printNumbersList(List<? extends Number> numbers) {
        System.out.println("Log input numbers: " + numbers);
    }

    public static <T extends Number> void printNewNumbers(T value) {
        System.out.println("Log input numbers: " + value);
    }

}
