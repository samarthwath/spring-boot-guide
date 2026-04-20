package com.revise.threading;

public class ThreadCreationWays {

    public static void main(String[] args) {
        InheritibleThread inheritibleThread = new InheritibleThread();
        inheritibleThread.setName("InheritibleThread");
        inheritibleThread.start();
        RunnableThread runnableThread = new RunnableThread();
        Thread threadRunnable = new Thread(runnableThread);
        threadRunnable.setName("RunnableThread");
        threadRunnable.start();

    }
}
