package com.revise.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBQ {
    private static final Integer BUFFER_SIZE = 10;
    private static BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(BUFFER_SIZE);

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        Thread threadProducerThread = new Thread(producer);
        Thread threadConsumerThread = new Thread(consumer);
        threadProducerThread.start();
        threadConsumerThread.start();
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            int item = 1;
            while (true) {
                try {
                    buffer.put(item++);
                    System.out.println("Item produced: " + (item - 1));
                    //Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Integer consumedItem = buffer.take();
                    System.out.println("Item consumed with item id: " + consumedItem);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
