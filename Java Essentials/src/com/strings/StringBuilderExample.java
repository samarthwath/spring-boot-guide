package com.strings;

public class StringBuilderExample {
    public static void main(String[] args) throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        Runnable runnableFirst = () -> {
            for (int iterate = 0; iterate < 1000; iterate++) {
                stringBuilder.insert(iterate, "A");
            }
        };
        Runnable runnableSecond = () -> {
            for (int iterate = 0; iterate < 1000; iterate++) {
                stringBuilder.insert(iterate, "B");
            }
        };
        Thread threadFirst = new Thread(runnableFirst);
        Thread threadSecond = new Thread(runnableSecond);
        threadFirst.start();
        threadSecond.start();
        threadFirst.join();
        threadSecond.join();
        System.out.println("Length of the string: " + stringBuilder.length());
    }
}
