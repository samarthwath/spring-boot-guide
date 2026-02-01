package com.revise.core.java.generics;

public class TestGenericClass {
    public static void main(String[] args) {
        GenericClass<Integer> genericClassInteger = new GenericClass<>();
        genericClassInteger.setValue(1);
        System.out.println("Log integer value: " + genericClassInteger.getValue());

        GenericClass<String> genericClassString = new GenericClass<>();
        genericClassString.setValue("Hello");
        System.out.println("Log string value: " + genericClassString.getValue());

        double sumValue = TestGenericClass.addNumbers(1234, 4567);
        System.out.println("Sum value is: " + sumValue);

        TestGenericClass.printNumbers(6786);
    }

    public static <T extends Number> double addNumbers(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    public static <T extends Integer> void printNumbers(T number) {
        System.out.println("Printing number: " + number);
    }
}
