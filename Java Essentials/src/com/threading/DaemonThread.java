package com.threading;

public class DaemonThread {
    public static void main(String[] args) {
        Runnable daemonRunnable = () -> {
            while (true) {
                System.out.println("Daemon thread is running !!!");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread daemonThread = new Thread(daemonRunnable, "Daemon Thread");
        daemonThread.setDaemon(true);
        System.out.println("Name: " + daemonThread.getName());
        System.out.println("Daemon thread state after instantiation: " + daemonThread.getState());
        daemonThread.start();
        System.out.println("Is thread daemon: " + daemonThread.isDaemon());
    }
}
