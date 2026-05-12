package com.revise.javafeatures.mystreams;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureWithExecutor {
    public static void main(String[] args) {
        System.out.println("Main Thread started");
        ExecutorService executor = Executors.newFixedThreadPool(1);
        CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + " is running");
                    return 100;
                }, executor)
                .thenApply(intValue -> intValue * 2)
                .thenAccept(value -> System.out.println(value));
        System.out.println("Main Thread finished");
        executor.shutdown();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<String> userCompletable = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " is running");
            return "User initiated Order";
        }, fixedThreadPool);
        CompletableFuture<String> orderCompletable = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " is running");
            return "Order submitted";
        }, fixedThreadPool);
        CompletableFuture<String> paymentCompletable = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " is running");
            return "Payment processed";
        }, fixedThreadPool);

        CompletableFuture
                .allOf(userCompletable, orderCompletable, paymentCompletable)
                .join();
        System.out.println(userCompletable.join());
        System.out.println(orderCompletable.join());
        System.out.println(paymentCompletable.join());
        System.out.println("Main Thread finished");

        userCompletable
                .thenCombine(orderCompletable, (p1, p2) -> p1 + p2)
                .thenCombine(paymentCompletable, (p1, p2) -> p1 + p2)
                .thenAccept(result -> System.out.println(result));


        fixedThreadPool.shutdown();
        executor.shutdown();


    }

}
