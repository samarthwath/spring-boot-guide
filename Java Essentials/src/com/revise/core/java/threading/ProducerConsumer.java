package com.revise.core.java.threading;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    private static Object lock = new Object();
    private static boolean isListFull = false;
    private static boolean isListEmpty = true;
    private static int BUFFER_SIZE = 10;
    private static List<Integer> BUFFER = new ArrayList<>(BUFFER_SIZE);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnableProducer = () -> {
            for (int i = 1; i <= 50; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock) {
                    while (isListFull) {
                        System.out.println("Producer is waiting");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Produced item: " + i);
                    BUFFER.add(i);
                    isListEmpty = false;
                    if (BUFFER.size() == BUFFER_SIZE) {
                        isListFull = true;
                    }
                    lock.notify();
                }
            }
        };

        Runnable runnableConsumer = () -> {
            for (int i = 1; i <= 50; i++) {
                synchronized (lock) {
                    while (isListEmpty) {
                        System.out.println("Consumer is waiting");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Integer consumedItem = BUFFER.remove(0);
                    System.out.println("Consumed item: " + consumedItem);
                    isListFull = false;
                    if (BUFFER.isEmpty()) {
                        isListEmpty = true;
                    }
                    lock.notify();
                }

            }
        };
        Thread producerThread = new Thread(runnableProducer);
        Thread consumerThread = new Thread(runnableConsumer);
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();

    }
}
