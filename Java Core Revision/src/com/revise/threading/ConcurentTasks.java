package com.revise.threading;

import java.util.concurrent.Semaphore;

public class ConcurentTasks {

    private final Semaphore semaphore = new Semaphore(3);

    public void displayMethod(String threadName) throws InterruptedException {
        semaphore.acquire();
        System.out.println(threadName + " has acquired the displayMethod");
        Thread.sleep(3000);
        semaphore.release();
        System.out.println(threadName + " has released the displayMethod");
    }

}
