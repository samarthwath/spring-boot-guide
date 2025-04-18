package com.threading;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreadExample {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        int numberOfThreads = 10_000_00;
        Runnable runnableFirst = () -> {
            System.out.println("Fetching data from the API.");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Data fetched from the API.");
        };

        for (int iterate = 0; iterate < numberOfThreads; iterate++) {
            System.out.println("Log iterate value: " + iterate);
            Thread thread = Thread.ofVirtual().unstarted(runnableFirst);
            //CPU capacity and memory.
            //Platform thread typically have a large thread stack and other resources.
            //thread.setDaemon(true);
            thread.setName("Thread: " + iterate);
            thread.start();
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            thread.join();
            System.out.println("Thread completed : " + thread.getName());
        }
    }
}
