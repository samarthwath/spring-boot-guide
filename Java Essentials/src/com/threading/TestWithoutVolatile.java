package com.threading;

public class TestWithoutVolatile {
    public static void main(String[] args) {
        SharedResourceWithoutVolatile sharedResourceWithoutVolatile = new SharedResourceWithoutVolatile();
        Runnable runnableFirst = () -> {
            System.out.println("Thread 1 started.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sharedResourceWithoutVolatile.setFlag(true);
            System.out.println("Flag set/updated by Thread 1.");
            System.out.println("Thread 1 completed.");
        };
        Thread thread1 = new Thread(runnableFirst, "Thread 1");
        thread1.start();
        Runnable runnableSecond = () -> {
            System.out.println("Thread 2 started.");
            while (!sharedResourceWithoutVolatile.getFlag()) {

            }
            System.out.println("Thread 2 completed.");
        };
        Thread thread2 = new Thread(runnableSecond, "Thread 2");
        thread2.start();

    }
}
