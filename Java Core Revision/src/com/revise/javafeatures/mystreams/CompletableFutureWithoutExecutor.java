package com.revise.javafeatures.mystreams;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureWithoutExecutor {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread started");
        CompletableFuture<String> helloCompletable = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello CompletableFuture";
        });

        CompletableFuture<String> hiCompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(6000);
                        System.out.println("Thread: " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return " Hi CompletableFuture";
                });

        CompletableFuture<String> heyThereCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(7000);
                System.out.println("Thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return " Hey There CompletableFuture";
        });


        helloCompletable
                .thenCombine(hiCompletableFuture, (hello, hi) -> hello + hi)
                .thenCombine(heyThereCompletableFuture, (combined, heyThere) -> combined + heyThere)
                .thenApply(finalResult -> finalResult.toUpperCase())
                .thenAccept(transformedResult -> System.out.println(transformedResult));


        //Making main thread go into sleep so that result can be displayed
        Thread.sleep(9000);
        System.out.println("Main Thread finished");

        //Without executor: It uses ForkJoinPool.commonPool() these generate daemon threads
        //And Daemon Threads does not keep JVM alive
        //Flow-> Main Thread finished -> JVM Terminated -> Async task killed.
        //Therefore without executor if we want to see it running
        //We might have to either use join or Thread.sleep() for the main Thread.


        //With executor service: It creates non-daemon worker threads.
        //These are user threads
        //Therefore JVM exit only when all user thread gets finished.
        //Therefore JVM stays alive and Async task gets chance to complete.


    }

}
