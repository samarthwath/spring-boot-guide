package com.revise.core.java.threading;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerRevise {
    private static int BUFFER_SIZE = 10;
    private static List<Integer> BUFFER = new ArrayList<>(BUFFER_SIZE);
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnableProducer = () -> {
            for (int i = 1; i <= 50; i++) {
                synchronized (lock) {
                    while (BUFFER.size() == BUFFER_SIZE) {
                        System.out.println(Thread.currentThread().getName() + " waiting");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Produced item: " + i);
                    BUFFER.add(i);
                    lock.notify();
                }
            }
        };

        Runnable runnableConsumer = () -> {
            for (int i = 1; i <= 50; i++) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock) {
                    while (BUFFER.isEmpty()) {
                        System.out.println(Thread.currentThread().getName() + " waiting");
                    }
                    Integer consumedItem = BUFFER.remove(0);
                    System.out.println("Consumed item: " + consumedItem);
                    lock.notify();
                }
            }
        };

        Thread producerThread = new Thread(runnableProducer);
        Thread consumerThread = new Thread(runnableConsumer);
        producerThread.setName("Producer Thread");
        consumerThread.setName("Consumer Thread");
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
    }

}
