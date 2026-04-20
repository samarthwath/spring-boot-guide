package com.revise.threading;

public class ReentrantReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        ProductPriceCache productPriceCache = new ProductPriceCache();
        Runnable readerTask = () -> {
            try {
                productPriceCache.readPrice(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable writerTask = () -> {
            try {
                productPriceCache.updatePrice(Thread.currentThread().getName(), 200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };


        Thread writerThreadFirst = new Thread(writerTask, "Thread-1-Writer");
        Thread writerThreadSecond = new Thread(writerTask, "Thread-2-Writer");
        Thread writerThreadThird = new Thread(writerTask, "Thread-3-Writer");
        Thread writerThreadFourth = new Thread(writerTask, "Thread-4-Writer");


        Thread readerThreadFirst = new Thread(readerTask, "Thread-1-Reader");
        Thread readerThreadSecond = new Thread(readerTask, "Thread-2-Reader");
        Thread readerThreadThird = new Thread(readerTask, "Thread-3-Reader");
        Thread readerThreadFourth = new Thread(readerTask, "Thread-4-Reader");

        readerThreadFirst.start();
        readerThreadSecond.start();




        writerThreadFirst.start();

        readerThreadThird.start();
        readerThreadFourth.start();

        writerThreadSecond.start();
        writerThreadThird.start();
        writerThreadFourth.start();

    }

}
