package com.revise.threading;

public class TestSharedCounter {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter sharedCounter = new SharedCounter();

        Runnable runnableThreadFirst = () -> {
            System.out.println(Thread.currentThread().getName() + " is running");
            for (int i = 1; i <= 2000; i++) {
                sharedCounter.incrementCounter();
            }
        };

        Runnable runnableThreadSecond = () -> {
            System.out.println(Thread.currentThread().getName() + " is running");
            for (int i = 1; i <= 2000; i++) {
                sharedCounter.incrementCounter();
            }
        };

        Thread threadFirst = new Thread(runnableThreadFirst, "Thread First");
        Thread threadSecond = new Thread(runnableThreadSecond, "Thread Second");
        threadFirst.start();
        threadSecond.start();

        threadFirst.join();
        threadSecond.join();

        System.out.println("Log counter value: " + sharedCounter.getCounter());

    }

}
