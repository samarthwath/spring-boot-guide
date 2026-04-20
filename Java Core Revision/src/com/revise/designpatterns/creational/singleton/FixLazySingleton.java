package com.revise.designpatterns.creational.singleton;

public class FixLazySingleton {
    private static FixLazySingleton fixLazySingleton;

    private FixLazySingleton() {

    }

    public static synchronized FixLazySingleton getInstance() {
        if (fixLazySingleton == null) {
            fixLazySingleton = new FixLazySingleton();
        }
        return fixLazySingleton;
    }
}
