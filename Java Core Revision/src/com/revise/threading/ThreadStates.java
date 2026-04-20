package com.revise.threading;

public class ThreadStates {
    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            try {
                ThreadStates.test();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread threadFirst = new Thread(runnable);
        Thread threadSecond = new Thread(runnable);
        threadFirst.start();
        threadSecond.start();
        Thread.sleep(1000);
        System.out.println(threadFirst.getName() + " state " + threadFirst.getState());
        System.out.println(threadSecond.getName() + " state " + threadSecond.getState());
    }

    public static synchronized void test() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Inside the synchronized method");
    }
}
