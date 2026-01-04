package com.revise.core.java.threading;

public class TestSharedCounterWithoutRace {
    public static void main(String[] args) throws InterruptedException {
        SharedCounterWithoutRace sharedCounterWithoutRace = new SharedCounterWithoutRace();
        Runnable runnableFirst = () -> {
            for (int i = 1; i <= 1000; i++) {
                sharedCounterWithoutRace.increement();
            }
        };
        Runnable runnableSecond = () -> {
            for (int i = 1; i <= 1000; i++) {
                sharedCounterWithoutRace.increement();
            }
        };
        Thread threadFirst = new Thread(runnableFirst);
        Thread threadSecond = new Thread(runnableSecond);
        threadFirst.start();
        threadSecond.start();
        threadFirst.join();
        threadSecond.join();
        System.out.println("Final thread counter value: " + sharedCounterWithoutRace.getCounter());
    }
}
