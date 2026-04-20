package com.revise.designpatterns.creational.singleton;

public class TestFixLazySingleton {
    public static void main(String[] args) {
        Runnable task = () -> {
            //System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println("HashCode: " + FixLazySingleton.getInstance().hashCode());
        };

        for (int i = 1; i <= 1115; i++) {
            new Thread(task).start();
        }
    }

}
