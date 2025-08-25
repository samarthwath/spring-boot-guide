package com.revise.thread;

public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set(100);

        Thread threadFirst = new Thread(() -> {
            int i = 67;
            threadLocal.set(1000);
            System.out.println("Log value: " + threadLocal.get());
            threadLocal.remove();
            System.out.println("Log value: " + threadLocal.get());
            System.out.println("Log i value: " + i);
        });
        threadFirst.start();
        threadFirst.join();
    }
}
