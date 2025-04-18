package com.threading;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedCounter {
    private int counter = 0;

    private AtomicInteger count = new AtomicInteger(0);

    //The same can be achieved by making increement with synchronized keyword.
    //Synchronization is lock based approach whereas atomic is non-lock based approach.
    //Atomic
    //kaninika2511@gmail.com
    //Sus@@6174

    public void increement() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void increementCount() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
