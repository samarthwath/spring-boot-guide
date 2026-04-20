package com.revise.threading;

public class ReentrantLockTestBlocking {
    public static void main(String[] args) {
        BankAccountNew bankAccountNew = new BankAccountNew();
        Runnable runnableTask = () -> {
            try {
                bankAccountNew.withdrawAmount(Thread.currentThread().getName(), 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread threadFirst = new Thread(runnableTask, "Thread-1");
        Thread threadSecond = new Thread(runnableTask, "Thread-2");
        Thread threadThird = new Thread(runnableTask, "Thread-3");

        threadFirst.start();
        threadSecond.start();
        threadThird.start();

    }

}
