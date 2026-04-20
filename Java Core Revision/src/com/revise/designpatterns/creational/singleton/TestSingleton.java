package com.revise.designpatterns.creational.singleton;

public class TestSingleton {
    public static void main(String[] args) {
        System.out.println("1st hashCode of SingletonFirst instance: " + LazySingleton.getSingletonFirstInstance().hashCode());
        System.out.println("2nd hashCode of SingletonFirst instance: " + LazySingleton.getSingletonFirstInstance().hashCode());
        System.out.println("3rd hashCode of SingletonFirst instance: " + LazySingleton.getSingletonFirstInstance().hashCode());


        System.out.println("1st hashCode of EagerSingleton: " + EagerSingleton.getEagerSingletonInstance().hashCode());
        System.out.println("2nd hashCode of EagerSingleton: " + EagerSingleton.getEagerSingletonInstance().hashCode());
        System.out.println("3rd hashCode of EagerSingleton: " + EagerSingleton.getEagerSingletonInstance().hashCode());

    }

}
