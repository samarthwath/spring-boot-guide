package com.revise.thread;

public class TestShareCounterWithAtomic {

    public static void main(String[] args) throws InterruptedException {
        ShareCounterWithAtomic shareCounterWithAtomic = new ShareCounterWithAtomic();
        Runnable runnableFirst = () -> {
            for (int i = 0; i < 100; i++) {
                shareCounterWithAtomic.increement();
            }
        };
        Runnable runnableSecond = () -> {
            for (int i = 0; i < 100; i++) {
                shareCounterWithAtomic.increement();
            }
        };
        Thread threadFirst = new Thread(runnableFirst, "threadFirst");
        Thread threadSecond = new Thread(runnableSecond, "threadSecond");
        threadFirst.start();
        threadSecond.start();

        threadFirst.join();
        threadSecond.join();

        System.out.println("Final result: " + shareCounterWithAtomic.getCount());
    }
}
