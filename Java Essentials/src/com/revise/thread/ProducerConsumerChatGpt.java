package com.revise.thread;


import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerChatGpt {
    private static final int BUFFER_SIZE = 50;
    private static final Object lock = new Object();

    private static boolean isListFull = false;
    private static boolean isListEmpty = true;

    private static final List<Integer> integerList = new ArrayList<>(BUFFER_SIZE);

    public static void main(String[] args) {

        Runnable producerRunnable = () -> {
            for (int index = 1; index <= 55; index++) {

                try {
                    Thread.sleep(1000); // simulate production delay (outside lock)
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (lock) {
                    // Wait while buffer is full
                    while (isListFull) {
                        try {
                            System.out.println("Producer is waiting...");
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // Add item to buffer
                    integerList.add(index);
                    System.out.println("Producer produced item: " + index);

                    // Update state flags
                    isListEmpty = false;
                    if (integerList.size() == BUFFER_SIZE) {
                        isListFull = true;
                    }

                    // Notify all waiting threads
                    lock.notifyAll();
                }
            }
        };

        Runnable consumerRunnable = () -> {
            for (int index = 1; index <= 55; index++) {

                try {
                    Thread.sleep(2000); // simulate consumption delay (outside lock)
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (lock) {
                    // Wait while buffer is empty
                    while (isListEmpty) {
                        try {
                            System.out.println("Consumer is waiting...");
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // Remove item from buffer
                    Integer removedElement = integerList.remove(0);
                    System.out.println("Consumer consumed item: " + removedElement);

                    // Update state flags
                    isListFull = false;
                    if (integerList.isEmpty()) {
                        isListEmpty = true;
                    }

                    // Notify all waiting threads
                    lock.notifyAll();
                }
            }
        };

        Thread consumerThread = new Thread(consumerRunnable);
        Thread producerThread = new Thread(producerRunnable);

        // Start both threads
        consumerThread.start();
        producerThread.start();
    }
}
