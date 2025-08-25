package com.revise.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerNewBQ {
    private static final Integer BUFFER = 10;
    private static BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(BUFFER);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnableProducer = () -> {
            int item = 1;
            while (true) {
                try {
                    System.out.println("Produced item: " + item);
                    buffer.put(item++);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable runnableConsumer = () -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    Integer consumedItem = buffer.take();
                    System.out.println("Consumed item: " + consumedItem);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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
