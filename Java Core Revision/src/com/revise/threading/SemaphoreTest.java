package com.revise.threading;

public class SemaphoreTest {
    public static void main(String[] args) {
        ConcurentTasks concurentTasks = new ConcurentTasks();
        Runnable task = () -> {
            try {
                concurentTasks.displayMethod(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread threadFirst = new Thread(task);
        Thread threadSecond = new Thread(task);
        Thread threadThird = new Thread(task);
        Thread threadFourth = new Thread(task);
        Thread threadFifth = new Thread(task);

        threadFirst.start();
        threadSecond.start();
        threadThird.start();
        threadFourth.start();
        threadFifth.start();
    }

}
