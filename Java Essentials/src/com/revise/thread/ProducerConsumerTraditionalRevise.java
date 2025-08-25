package com.revise.thread;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerTraditionalRevise {
    private static final int BUFFER_SIZE = 10;
    private static final Object lock = new Object();
    private static final List<Integer> buffer = new ArrayList<>(BUFFER_SIZE);
    private static boolean isListEmpty = true;
    private static boolean isListFull = false;

    public static void main(String[] args) {
        Runnable producer = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock) {
                for (int element = 1; element < 15; element++) {
                    while (isListFull) {
                        System.out.println("Produce is waiting.....");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Producer produced item: " + element);
                    buffer.add(element);
                    isListEmpty = false;
                    if (buffer.size() == BUFFER_SIZE) {
                        isListFull = true;
                    }
                    lock.notify();
                }
            }
        };
        Runnable consumer = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock) {
                while (isListEmpty) {
                    try {
                        System.out.println("Consumer is waiting....");
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Integer removedElement = buffer.remove(0);
                System.out.println("Consumer consumed item: " + removedElement);
                isListFull = false;
                if (buffer.size() == 0) {
                    isListEmpty = true;
                }
                lock.notify();
            }
        };

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}
