package com.revise.designpatterns.creational.singleton;

public class DoubleCheckedSingleton {
    private static DoubleCheckedSingleton doubleCheckedSingleton;

    private DoubleCheckedSingleton() {

    }

    public static DoubleCheckedSingleton getInstance() {
        if (doubleCheckedSingleton == null) {
            synchronized (DoubleCheckedSingleton.class) {
                doubleCheckedSingleton = new DoubleCheckedSingleton();
            }
        }
        return doubleCheckedSingleton;
    }

}
