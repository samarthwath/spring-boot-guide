package com.revise.designpatterns.creational.singleton;

public class EagerSingleton {
    private static final EagerSingleton eagerSingleton = new EagerSingleton();

    private EagerSingleton() {

    }

    public static EagerSingleton getEagerSingletonInstance() {
        return eagerSingleton;
    }
}
