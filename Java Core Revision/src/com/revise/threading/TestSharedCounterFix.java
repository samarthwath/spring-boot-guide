package com.revise.threading;

public class TestSharedCounterFix {

    public static void main(String[] args) throws InterruptedException {
        SharedCounterFix sharedCounterFix = new SharedCounterFix();

        Runnable runnableFirst = () -> {
            System.out.println(Thread.currentThread().getName() + " is running");
            for (int i = 1; i <= 2000; i++) {
                sharedCounterFix.incrementCounter();
            }
        };

        Runnable runnableSecond = () -> {
            System.out.println(Thread.currentThread().getName() + " is running");
            for (int i = 1; i <= 2000; i++) {
                sharedCounterFix.incrementCounter();
            }
        };

        Thread threadFirst = new Thread(runnableFirst);
        Thread threadSecond = new Thread(runnableSecond);
        threadFirst.start();
        threadSecond.start();

        threadFirst.join();
        threadSecond.join();

        System.out.println("Log counter value: " + sharedCounterFix.getCounter());

    }

}
