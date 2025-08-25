package com.revise.thread;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerTest {
    private static boolean isListFull = false;
    private static boolean isListEmpty = false;
    private static final int BUFFER_SIZE = 10;
    private static List<Integer> buffer = new ArrayList<>(BUFFER_SIZE);
    private static Object lock = new Object();

    public static void main(String[] args) {

        Runnable producerRunnable = () -> {
            for (int element = 1; element <= 15; element++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock) {
                    while (isListFull) {
                        System.out.println("Producer is waiting...");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    boolean producedElement = buffer.add(element);
                    System.out.println("Produced element: " + element);
                    if (buffer.size() == BUFFER_SIZE) {
                        isListFull = true;
                    }
                    isListEmpty = false;
                    lock.notify();
                }
            }
        };

        Runnable consumerRunnable = () -> {
            for (int element = 1; element <= 15; element++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock) {
                    while (isListEmpty) {
                        System.out.println("Consumer is waiting....");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Integer consumedItem = buffer.remove(0);
                    System.out.println("Consumed item: " + consumedItem);
                    if (buffer.size() == 0) {
                        isListEmpty = true;
                    }
                    isListFull = false;
                    lock.notify();
                }

            }
        };

        Thread producerThread = new Thread(producerRunnable);
        Thread consumerThread = new Thread(consumerRunnable);

        producerThread.start();
        consumerThread.start();

    }
}
