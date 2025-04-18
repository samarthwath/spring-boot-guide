package com.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolsExample {
    public static void main(String[] args) {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int iterate = 1; iterate <= 5; iterate++) {
            final int taskId = iterate;
            System.out.println("Single Thread Pool Task Id: " + taskId);
            singleThreadPool.execute(() -> {
                System.out.println("Single Thread Pool Task: " + taskId + " handled by: " + Thread.currentThread().getName());
            });
        }
        singleThreadPool.shutdown();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int iterate = 1; iterate <= 5; iterate++) {
            final int taskId = iterate;
            System.out.println("Fixed Thread Pool Task Id: " + taskId);
            fixedThreadPool.execute(() -> {
                System.out.println("Fixed Thread Pool Task: " + taskId + " handled by: " + Thread.currentThread().getName());
            });
        }
        fixedThreadPool.shutdown();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int iterate = 1; iterate <= 5; iterate++) {
            final int taskId = iterate;
            System.out.println("Cached Thread Pool Task Id: " + taskId);
            cachedThreadPool.execute(() -> {
                System.out.println("Cached Thread Pool Task: " + taskId + " handled by: " + Thread.currentThread().getName());
            });
        }
        cachedThreadPool.shutdown();
        ExecutorService scheduleThreadPool = Executors.newScheduledThreadPool(3);
    }
}
