package com.revise.threading;

public class TestAtomicCounter {
    public static void main(String[] args) throws InterruptedException {
        AtomicCounter atomicCounter = new AtomicCounter();

        Runnable runnableFirst = () -> {
            System.out.println(Thread.currentThread().getName() + " started");
            for (int i = 1; i <= 2000; i++) {
                atomicCounter.increment();
            }
        };

        Runnable runnableSecond = () -> {
            System.out.println(Thread.currentThread().getName() + " started");
            for (int i = 1; i <= 2000; i++) {
                atomicCounter.increment();
            }
        };

        Thread threadFirst = new Thread(runnableFirst, "Thread First");
        Thread threadSecond = new Thread(runnableSecond, "Thread Second");

        threadFirst.start();
        threadSecond.start();

        threadFirst.join();
        threadSecond.join();

        System.out.println("Counter value: " + atomicCounter.getCounter());


    }

}
