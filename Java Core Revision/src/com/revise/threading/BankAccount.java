package com.revise.threading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int savingsAmount = 2000;
    private final ReentrantLock lock = new ReentrantLock(true);

    public void withdrawAmount(String threadName, int amount) throws InterruptedException {
        System.out.println(threadName + " is trying to acquire the lock");
        if (lock.tryLock(12, TimeUnit.SECONDS)) {
            System.out.println(threadName + " has acquired the lock");
            Thread.sleep(3000);
            processWithdrawTransaction(threadName, amount);
            lock.unlock();
        } else {
            System.out.println(threadName + " is unable to acquire the lock");
        }

    }

    private void processWithdrawTransaction(String threadName, int amount) {
        lock.lock();
        System.out.println("Lock hold count for current thread: " + lock.getHoldCount());
        if (amount <= savingsAmount) {
            savingsAmount = savingsAmount - amount;
            System.out.println(threadName + " has deducted the amount");
            System.out.println("Current saving amount: " + savingsAmount);
        } else {
            System.out.println("No sufficient amount left in saving account");
        }
        lock.unlock();
    }
}
