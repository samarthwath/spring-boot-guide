package com.revise.threading;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccountNew {
    private int savingsAmount = 2000;
    private final ReentrantLock lock = new ReentrantLock();

    public void withdrawAmount(String threadName, int amount) throws InterruptedException {
        try {
            lock.lock();
            System.out.println(threadName + " has acquired the lock");
            System.out.println("Lock hold count: " + lock.getHoldCount());
            Thread.sleep(3000);
            processWithDrawAmount(threadName, amount);
        } finally {
            lock.unlock();
            System.out.println(threadName + " has release the lock");
        }
    }

    private void processWithDrawAmount(String threadName, int amount) {
        try {
            lock.lock();
            System.out.println("Lock hc: " + lock.getHoldCount());
            if (amount <= savingsAmount) {
                savingsAmount -= amount;
                System.out.println(threadName + " has deducted the saving amount");
                System.out.println("Current savings amount: " + savingsAmount);
            }
        } finally {
            lock.unlock();
        }
    }

}
