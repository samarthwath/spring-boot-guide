package com.threading;

public class RaceConditionExample {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter sharedCounter = new SharedCounter();

        Runnable runnableFirst = () -> {
            System.out.println("Thread 1 started");
            for (int iterate = 0; iterate < 20000; iterate++) {
                sharedCounter.increement();
            }
        };
        Thread thread1 = new Thread(runnableFirst, "Thread 1");
        thread1.start();
        Runnable runnableSecond = () -> {
            System.out.println("Thread 2 started.");
            for (int iterate = 0; iterate < 20000; iterate++) {
                sharedCounter.increement();
            }
        };
        Thread thread2 = new Thread(runnableSecond, "Thread 2");
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Log counter value after execution of threads: ");
        System.out.println(sharedCounter.getCounter());
    }
}
