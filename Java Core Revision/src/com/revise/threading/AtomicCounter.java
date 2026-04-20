package com.revise.threading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger counter = new AtomicInteger();

    public void increment() {
        counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }

}
