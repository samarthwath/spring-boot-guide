package com.threading;

public class DaemonThreadExampleSecond {

    public static void main(String[] args) {
        Runnable runnableFirst = () -> {
            while (true) {
                System.out.println("Daemon thread is running. " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread daemonThread = new Thread(runnableFirst, "Daemon Thread");
        daemonThread.setDaemon(true);
        daemonThread.start();

        try {
            System.out.println(Thread.currentThread().getName() + " about to sleep.");
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
