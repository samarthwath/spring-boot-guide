package com.revise.core.java.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(3);
        System.out.println("Fixed thread pool: ");
        for (int i = 1; i <= 10; i++) {
            final int task = i;
            fixedExecutorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " executed task: " + task);
            });
        }

        ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
        System.out.println("Cached thread pool: ");
        for (int i = 1; i <= 10; i++) {
            final int task = i;
            cachedExecutorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " executed task: " + task);
            });
        }

        Thread.ofVirtual().start(() -> {
            System.out.println(Thread.currentThread().getName() + " running");
        });


    }
}
