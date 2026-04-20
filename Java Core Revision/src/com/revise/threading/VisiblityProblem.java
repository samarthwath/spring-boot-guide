package com.revise.threading;

public class VisiblityProblem {

    public static void main(String[] args) throws InterruptedException {
        VisiblityShared visiblityShared = new VisiblityShared();

        Runnable runnableFirst = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " started");
            while (visiblityShared.getFlag()) {

            }
        };

        Runnable runnableSecond = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " started");
            visiblityShared.changeFlag();
        };

        Thread threadFirst = new Thread(runnableFirst, "Thread First");
        Thread threadSecond = new Thread(runnableSecond, "Thread Second");

        threadSecond.start();
        threadFirst.start();


        //System.out.println("Both threads started");
        threadFirst.join();
        threadSecond.join();
    }
}
