package com.revise.thread;

public class VolatileExample {
    private static volatile boolean isThreadRunning = true;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnableFirst = () -> {
            while (isThreadRunning) {
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        };
        Runnable runnableSecond = () -> {
            System.out.println(Thread.currentThread().getName() + "is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("About to stop threadFirst: ");
            isThreadRunning = false;
        };

        Thread threadFirst = new Thread(runnableFirst, "threadFirst");
        Thread threadSecond = new Thread(runnableSecond, "threadSecond");
        threadFirst.start();
        threadSecond.start();

        threadFirst.join();
        threadSecond.join();

    }
}
