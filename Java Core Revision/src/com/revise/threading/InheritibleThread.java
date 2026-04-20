package com.revise.threading;

public class InheritibleThread extends Thread {

    @Override
    public void run() {
        System.out.println("Hi from: " + Thread.currentThread().getName());
    }
}
