package com.revise.threading;

public class VisiblityProblemFix {
    public static void main(String[] args) throws InterruptedException {
        VisiblitySharedFix visiblitySharedFix = new VisiblitySharedFix();

        Runnable runnableFirst = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " started");
            while (visiblitySharedFix.getFlag()) {

            }
        };

        Runnable runnableSecond = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " started");
            visiblitySharedFix.changeFlag();
        };

        Thread threadFirst = new Thread(runnableFirst, "Thread First");
        Thread threadSecond = new Thread(runnableSecond, "Thread Second");

        threadFirst.start();
        threadSecond.start();

        threadFirst.join();
        threadSecond.join();

    }
}
