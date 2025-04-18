package com.threading;

import java.util.concurrent.BlockingQueue;

public class ProducerBlockingQueue implements Runnable {

    private BlockingQueue<Integer> queue;

    public ProducerBlockingQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int index = 1; index <= 200; index++) {
            try {
                //Thread.sleep(2000);
                queue.put(index);
                System.out.println("Produced item: " + index);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
