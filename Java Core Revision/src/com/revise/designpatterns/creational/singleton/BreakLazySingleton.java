package com.revise.designpatterns.creational.singleton;

public class BreakLazySingleton {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Thread working: " + Thread.currentThread().getName());
            System.out.println("HashCode of LazySingleton instance: " + LazySingleton.getSingletonFirstInstance().hashCode());
        };

        for (int i = 0; i <= 5; i++) {
            new Thread(task).start();
        }

    }

}
