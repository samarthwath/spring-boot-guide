package com.revise.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ShareCounterWithAtomic {

    private AtomicInteger counter = new AtomicInteger();

    public void increement() {
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }

}
