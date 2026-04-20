package com.revise.threading;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        Runnable task = () -> {
            try {
                semaphore.acquire();
                logThreadAccess(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " has acquired");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + " has released");
            }
        };


        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
            System.out.println(thread.getName() + " state: " + thread.getState());
        }
    }

    /**
     * Below method behaves as CS
     *
     * @param threadName
     */
    private static void logThreadAccess(String threadName) {
        System.out.println(threadName + " accessed CS and called Dummy API");
    }

}
