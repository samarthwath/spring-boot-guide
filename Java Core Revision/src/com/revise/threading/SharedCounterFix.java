package com.revise.threading;

public class SharedCounterFix {
    private int counter;

    public synchronized void incrementCounter() {
        counter++;
    }

    public synchronized int getCounter() {
        return counter;
    }
}
