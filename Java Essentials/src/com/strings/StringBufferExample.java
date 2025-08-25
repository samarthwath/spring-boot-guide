package com.strings;

public class StringBufferExample {
    public static void main(String[] args) throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        Runnable runnableFirst = () -> {
            for (int iterate = 0; iterate < 1000; iterate++) {
                stringBuffer.insert(iterate, "A");
            }
        };
        Runnable runnableSecond = () -> {
            for (int iterate = 0; iterate < 1000; iterate++) {
                stringBuffer.insert(iterate, "B");
            }
        };
        Thread threadFirst = new Thread(runnableFirst);
        Thread threadSecond = new Thread(runnableSecond);
        threadFirst.start();
        threadSecond.start();
        threadFirst.join();
        threadSecond.join();
        System.out.println("Length of the string: " + stringBuffer.length());
    }
}
