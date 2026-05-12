package com.revise.javafeatures.mystreams;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableExampleSecond {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable<Integer> firstTask = () -> {
            System.out.println("Thread: "+Thread.currentThread().getName());
            Thread.sleep(3000);
            return 100;
        };
        Callable<Integer> secondTask = () -> {
            System.out.println("Thread: "+Thread.currentThread().getName());
            Thread.sleep(4000);
            return 200;
        };
        Callable<Integer> thirdTask = () -> {
            System.out.println("Thread: "+Thread.currentThread().getName());
            Thread.sleep(5000);
            return 300;
        };

        List<Callable<Integer>> callables = Arrays.asList(firstTask, secondTask, thirdTask);

        ExecutorService executor = Executors
                .newFixedThreadPool(2);


        Future<Integer> firstFuture = executor.submit(firstTask);
        Future<Integer> secondFuture = executor.submit(secondTask);
        Future<Integer> thirdFuture = executor.submit(thirdTask);
        System.out.println("Main Thread continues");
        System.out.println(firstFuture.get());
        System.out.println(secondFuture.get());
        System.out.println(thirdFuture.get());
        System.out.println("Main Thread still running");
        executor.shutdown();
    }
}
