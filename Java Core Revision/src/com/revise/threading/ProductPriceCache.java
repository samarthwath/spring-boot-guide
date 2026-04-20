package com.revise.threading;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProductPriceCache {
    private double price = 1000;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void readPrice(String threadName) throws InterruptedException {
        lock.readLock().lock();
        System.out.println(threadName + " has acquired the lock");
        Thread.sleep(1000);
        System.out.println(threadName + " reading the price: " + price);
        lock.readLock().unlock();
    }


    public void updatePrice(String threadName, double price) throws InterruptedException {
        lock.writeLock().lock();
        System.out.println(threadName + " has acquired the lock");
        Thread.sleep(6000);
        this.price = this.price + price;
        System.out.println(threadName + " has updated the price: " + this.price);
        lock.writeLock().unlock();
    }

}
