package com.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                1,
                5,
                0l,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2)
        );
        for (int iterate = 1; iterate <= 5; iterate++) {
            final int taskId = iterate;
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread pool executor: " +
                        "Thread Task: " + taskId +
                        "executed by thread: " + Thread.currentThread().getName()
                );
            });
        }
    }
}
