package com.revise.thread;

public class TestSharedCounter {

    public static void main(String[] args) throws InterruptedException {
        SharedCounter sharedCounter = new SharedCounter();

        Runnable runnableFirst = () -> {
            for (int iterate = 1; iterate < 100; iterate++) {
                sharedCounter.increement();
            }
        };

        Runnable runnableSecond = () -> {
            for (int iterate = 1; iterate < 100; iterate++) {
                sharedCounter.increement();
            }
        };

        Thread threadFirst = new Thread(runnableFirst, "firstThread");
        Thread threadSecond = new Thread(runnableSecond, "secondThread");
        threadFirst.start();
        threadSecond.start();

        threadFirst.join();
        threadSecond.join();

        System.out.println("Final result: " + sharedCounter.getCounter());
    }
}
