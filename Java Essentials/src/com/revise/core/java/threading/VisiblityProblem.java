package com.revise.core.java.threading;

public class VisiblityProblem {
    //private static boolean isThreadRunning = true;

    private static volatile boolean isThreadRunning = true;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnableFirst = () -> {
            System.out.println(Thread.currentThread().getName() + " started");
            while (isThreadRunning) {
            }
            System.out.println(Thread.currentThread().getName() + " stopped");
        };
        Runnable runnableSecond = () -> {
            System.out.println(Thread.currentThread().getName() + " started");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isThreadRunning = false;
            System.out.println(Thread.currentThread().getName() + " stopped");
        };
        Thread threadFirst = new Thread(runnableFirst);
        Thread threadSecond = new Thread(runnableSecond);
        threadFirst.start();
        threadSecond.start();
        threadFirst.join();
        threadSecond.join();
    }
}
