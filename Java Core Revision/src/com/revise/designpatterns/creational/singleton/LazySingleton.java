package com.revise.designpatterns.creational.singleton;

public class LazySingleton {
    private static LazySingleton singletonFirst;

    private LazySingleton() {

    }

    public static LazySingleton getSingletonFirstInstance() {
        if (singletonFirst == null) {
            singletonFirst = new LazySingleton();
            return singletonFirst;
        } else {
            return singletonFirst;
        }
    }
}
