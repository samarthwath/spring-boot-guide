package com.threading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestProducerConsumerBQ {
    public static void main(String[] args) {
        //Capacity is the size of the shared queue.
        //Whereas the loop iterations in each thread can be considered as the data elements
        //which needs to be exchanged between Producer and Consumer. 
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
        ProducerBlockingQueue producerBlockingQueue = new ProducerBlockingQueue(queue);
        ConsumerBlockingQueue consumerBlockingQueue = new ConsumerBlockingQueue(queue);
        Thread producerThread = new Thread(producerBlockingQueue);
        Thread consumerThread = new Thread(consumerBlockingQueue);
        producerThread.start();
        consumerThread.start();
    }
}
