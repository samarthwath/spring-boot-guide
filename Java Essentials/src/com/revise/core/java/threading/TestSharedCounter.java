package com.revise.core.java.threading;

public class TestSharedCounter {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter sharedCounter = new SharedCounter();
        Runnable runnableFirst = () -> {
            for (int i = 1; i <= 1000; i++) {
                sharedCounter.increement();
            }
        };

        Runnable runnableSecond = () -> {
            for (int i = 1; i <= 1000; i++) {
                sharedCounter.increement();
            }
        };

        Thread threadFirst = new Thread(runnableFirst);
        Thread threadSecond = new Thread(runnableSecond);
        threadFirst.start();
        threadSecond.start();
        threadFirst.join();
        threadSecond.join();
        System.out.println("Thread final counter value due to race condition: " + sharedCounter.getValue());
    }
}
