package com.revise.core.java.threading;

import java.util.ArrayList;
import java.util.List;

public class BestProducerConsumer {
    private static int BUFFER_SIZE = 10;
    private static Object lock = new Object();
    private static List<Integer> buffer = new ArrayList<>(BUFFER_SIZE);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnableProducer = () -> {
            for (int i = 1; i <= 50; i++) {
                synchronized (lock) {
                    while (buffer.size() == BUFFER_SIZE) {
                        System.out.println("Producer is waiting");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Produced item: " + i);
                    buffer.add(i);
                    lock.notifyAll();
                }
            }
        };

        Runnable runnableConsumer = () -> {
            for (int i = 1; i <= 50; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock) {
                    while (buffer.isEmpty()) {
                        System.out.println("Consumer is waiting");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Integer consumedElement = buffer.remove(0);
                    System.out.println("Consumed item: " + consumedElement);
                    lock.notifyAll();
                }
            }
        };

        Thread producer = new Thread(runnableProducer);
        Thread consumer = new Thread(runnableConsumer);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
