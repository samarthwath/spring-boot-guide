package com.revise.threading;

public class ThreadBlocked {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Runnable taskFirst = () -> {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " has acquired lock");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread threadFirst = new Thread(taskFirst);
        Thread threadSecond = new Thread(taskFirst);
        threadFirst.start();
        //Thread.sleep(3000);
        threadSecond.start();
        System.out.println(threadFirst.getName() + " state: " + threadFirst.getState());
        System.out.println(threadSecond.getName() + " state: " + threadSecond.getState());
    }
}
