package com.revise.core.java.threading;

public class TestThreadExtends {
    public static void main(String[] args) throws InterruptedException {
        ThreadFirstExtends threadFirstExtends = new ThreadFirstExtends();
        ThreadSecondExtends threadSecondExtends = new ThreadSecondExtends();
        threadFirstExtends.start();
        threadSecondExtends.start();
        threadFirstExtends.join();
        threadSecondExtends.join();
    }
}
