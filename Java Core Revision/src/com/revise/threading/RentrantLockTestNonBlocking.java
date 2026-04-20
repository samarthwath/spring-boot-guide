package com.revise.threading;

public class RentrantLockTestNonBlocking {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        Runnable runnableTask = () -> {
            try {
                bankAccount.withdrawAmount(Thread.currentThread().getName(), 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread threadFirst = new Thread(runnableTask, "Thread-1");
        Thread threadSecond = new Thread(runnableTask, "Thread-2");
        Thread threadThird = new Thread(runnableTask, "Thread-3");
        Thread threadFourth = new Thread(runnableTask, "Thread-4");

        threadFirst.start();
        threadSecond.start();
        threadThird.start();
        threadFourth.start();
    }

}
