package com.revise.threading;

public class SharedCounter {
    private int counter;

    /**
     * Method to increment counter value
     */
    public void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
