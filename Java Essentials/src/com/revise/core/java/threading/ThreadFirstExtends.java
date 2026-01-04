package com.revise.core.java.threading;

public class ThreadFirstExtends extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("Inside run method of: " + Thread.currentThread().getName());
        }
    }
}
