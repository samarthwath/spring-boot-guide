package com.revise.javafeatures.mystreams;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureSequential {
    public static void main(String[] args) {
        System.out.println("Main Thread started");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return getUserDetails();
                }, executor)
                .thenCompose(user -> CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return getOrderDetails(user);
                }, executor))
                .thenCompose(orderDetails -> CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return getPaymentDetails(orderDetails);
                }, executor))
                .thenApply(finalResult -> finalResult.toUpperCase())
                .thenAccept(result -> System.out.println(result));
        System.out.println("Main Thread finished");
        voidCompletableFuture.join();
        executor.shutdown();
    }

    private static String getUserDetails() {
        return "Username: SAMARTH";
    }

    private static String getOrderDetails(String userInfo) {
        return userInfo + ": Order Value: 10000";
    }

    private static String getPaymentDetails(String orderInfo) {
        return orderInfo + ": Payment Mode: UPI : Payment Value: 10000";
    }

}
