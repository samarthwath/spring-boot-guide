package com.threading;

import java.util.concurrent.Callable;

//Legacy way to create thread.
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Inside the 1st thread !!");
    }
}


public class FirstThread {
    public static void main(String[] args) {
        System.out.println("Hello World !!");
        MyThread myThread = new MyThread();
        myThread.start();
        //2nd method to create a thread.
        Thread threadSecond = new Thread(() -> {
            System.out.println("Inside the 2nd thread !!");
        }, "threadSecond");
        threadSecond.start();
        Runnable threadRunnableThird = () -> {
            System.out.println("Inside the 3rd thread started!!");
            System.out.println("Name: " + Thread.currentThread().getName());
            System.out.println("State before: " + Thread.currentThread().getState());
            try {
                Thread.sleep(3000);
                System.out.println("State in between: " + Thread.currentThread().getState());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("State after: " + Thread.currentThread().getState());
            System.out.println("3rd thread completed !!");
        };
        Thread threadThird = new Thread(threadRunnableThird, "Runnable thread.");
        threadThird.start();

        Runnable daemonRunnable = () -> {
            while (true) {
                System.out.println("Daemon thread is running !!!");
                try {
                    Thread.sleep(1000);
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

        Callable callable = () -> {
            return true;
        };
    }

}
