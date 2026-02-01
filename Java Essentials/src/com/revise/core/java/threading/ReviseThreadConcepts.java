package com.revise.core.java.threading;

public class ReviseThreadConcepts {
    private static volatile boolean isThreadRunning = true;

    public static void main(String[] arg) throws InterruptedException {

        //Code for the visibility problem: Solution add volatile keyword to the shared variable
        Runnable runnableFirst = () -> {
            ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
            threadLocal.set(100);
            System.out.println("Thread local variable value: " + threadLocal.get());
            threadLocal.remove();
            System.out.println(Thread.currentThread().getName() + " started");
            while (isThreadRunning) {

            }
            System.out.println(Thread.currentThread().getName() + " stopped");
        };
        Runnable runnableSecond = () -> {
            ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
            threadLocal.set(200);
            System.out.println("Thread local variable value: " + threadLocal.get());
            threadLocal.remove();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " started");
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
