package com.threading;

public class WaitNotifyExampleSecond {
    private static final Object lock = new Object();
    private static boolean condition = false;

    public static void main(String[] args) {
        Runnable consumerRunnable = () -> {
            System.out.println("Consumer is waiting......" + Thread.currentThread().getName());
            synchronized (lock) {
                while (!condition) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            System.out.println("Consumer: Resource consumed by Thread: " + Thread.currentThread().getName());
        };

        Runnable producerRunnable = () -> {
            System.out.println("Producer: Resource is getting created...");
            synchronized (lock) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                condition = true;
                lock.notify();
            }
            System.out.println("Producer: Resource Produced.");
        };
        Thread consumerThread = new Thread(consumerRunnable);
        consumerThread.setName("Consumer A");
        Thread consumerThreadSecond = new Thread(consumerRunnable);
        consumerThreadSecond.setName("Consumer B");
        Thread producerThread = new Thread(producerRunnable);
        consumerThread.start();
        consumerThreadSecond.start();
        producerThread.start();
    }

}
