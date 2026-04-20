package com.revise.designpatterns.creational.singleton;

public class TestDoubleCheckedSingleton {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("hashCode: " + DoubleCheckedSingleton.getInstance().hashCode());
        };
        for (int i = 1; i <= 15; i++) {
            new Thread(task).start();
        }

    }

}
