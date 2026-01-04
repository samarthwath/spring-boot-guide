package com.revise.core.java.threading;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedCounterAtomic {
    //With this concept there is no need for synchronization
    //and explicit locking is not required.

    private AtomicInteger counter = new AtomicInteger();

    public void increement() {
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}
