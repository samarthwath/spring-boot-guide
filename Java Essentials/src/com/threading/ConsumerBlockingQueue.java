package com.threading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConsumerBlockingQueue implements Runnable {

    private BlockingQueue<Integer> queue;

    public ConsumerBlockingQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int index = 1; index <= 200; index++) {
            try {
                Thread.sleep(3000);
                Integer consumedItem = queue.take();
                System.out.println("Consumed Item: " + consumedItem);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
