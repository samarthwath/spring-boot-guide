package com.threading;

public class VolatileExample {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Runnable runnableFirst = () -> {
            System.out.println("Thread 1 started.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 1 completed.");
            sharedResource.setFlag(true);
            System.out.println("Flag set by Thread 1.");
        };
        Thread thread1 = new Thread(runnableFirst, "Thread 1");
        thread1.start();
        Runnable runnableSecond = () -> {
            System.out.println("Thread 2 started.");
            while (!sharedResource.getFlag()) {

            }
            System.out.println("Thread 2 completed.");
        };
        Thread thread2 = new Thread(runnableSecond, "Thread 2");
        thread2.start();
    }
}
