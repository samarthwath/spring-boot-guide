package com.revise.core.java.threading;

public class SharedCounterWithoutRace {
    private int counter;

    public void increement() {
        synchronized (this) {
            counter++;
        }
    }

    public int getCounter() {
        return counter;
    }
}
