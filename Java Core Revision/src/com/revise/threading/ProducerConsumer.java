package com.revise.threading;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    private static Object lock = new Object();
    private static int BUFFER_SIZE = 10;
    private static List<Integer> BUFFER = new ArrayList<>(BUFFER_SIZE);

    public static void main(String[] args) throws InterruptedException {

        Runnable producer = () -> {
            for (int i = 1; i <= 50; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock) {
                    while (BUFFER.size() == BUFFER_SIZE) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " waiting");
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

        Runnable consumer = () -> {
            for (int i = 1; i <= 50; i++) {
                synchronized (lock) {
                    while (BUFFER.isEmpty()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " waiting");
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Integer remove = BUFFER.remove(0);
                    System.out.println("Consumed item: " + remove);
                    lock.notify();
                }
            }
        };

        Thread producerThread = new Thread(producer, "PRODUCER");
        Thread consumerThread = new Thread(consumer, "CONSUMER");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

    }

}
