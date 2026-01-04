package com.revise.core.java.threading;

public class TestSharedCounterAtomic {

    public static void main(String[] args) throws InterruptedException {
        SharedCounterAtomic sharedCounterAtomic = new SharedCounterAtomic();
        Runnable runnableFirst = () -> {
            for (int i = 1; i <= 1000; i++) {
                sharedCounterAtomic.increement();
            }
        };

        Runnable runnableSecond = () -> {
            for (int i = 1; i <= 1000; i++) {
                sharedCounterAtomic.increement();
            }
        };

        Thread threadFirst = new Thread(runnableFirst);
        Thread threadSecond = new Thread(runnableSecond);
        threadFirst.start();
        threadSecond.start();
        threadFirst.join();
        threadSecond.join();
        System.out.println("Final counter value: " + sharedCounterAtomic.getCount());
    }
}
