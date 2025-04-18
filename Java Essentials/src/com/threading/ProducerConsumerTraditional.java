package com.threading;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerTraditional {
    private static final int BUFFER_SIZE = 50;
    private static final Object lock = new Object();
    private static boolean isListFull = false;
    private static boolean isListEmpty = true;
    private static final List<Integer> integerList = new ArrayList<>(BUFFER_SIZE);

    public static void main(String[] args) throws InterruptedException {
//        List<Integer> integerList = new ArrayList<>(BUFFER_SIZE);
        Runnable producerRunnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int index = 1; index <= 55; index++) {
                synchronized (lock) {
                    while (isListFull) {
                        System.out.println("Producer is waiting.....");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Producer produced item: " + index);
                    integerList.add(index);
                    isListEmpty = false;
                    if (integerList.size() == BUFFER_SIZE) {
                        isListFull = true;
                    }
                    lock.notify();
                }
            }
        };
        Runnable consumerRunnable = () -> {
            for (int index = 1; index <= 55; index++) {
                synchronized (lock) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    while (isListEmpty) {
                        try {
                            System.out.println("Consumer is waiting....");
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Integer removedElement = integerList.remove(0);
                    System.out.println("Consumer consumed item: " + removedElement);
                    if (integerList.size() == 0) {
                        isListEmpty = true;
                    }
                    isListFull = false;
                    lock.notify();
                }
            }
        };
        Thread consumerThread = new Thread(consumerRunnable);
        Thread producerThread = new Thread(producerRunnable);
        consumerThread.start();
        producerThread.start();

    }
}
