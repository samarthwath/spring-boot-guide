package com.revise.thread;

public class SharedCounter {

    private int counter;

    public void increement() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

}
