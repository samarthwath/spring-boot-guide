package com.revise.core.java.threading;

public class SharedCounter {

    private int counter;

    public void increement() {
        counter++;
    }

    public int getValue() {
        return counter;
    }
}
