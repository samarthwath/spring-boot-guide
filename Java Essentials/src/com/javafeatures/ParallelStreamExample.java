package com.javafeatures;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class ParallelStreamExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> stringList = Arrays.asList("Hello", "World", "Geeks", "Hey there!!", "I am at the top", "of", "The world", "Helly Guys!");
        stringList
                .parallelStream()
                .forEach(element -> System.out.println("Element: " + element + " processed by: " + Thread.currentThread().getName()));

        stringList
                .stream()
                .forEach(element -> System.out.println("Element: " + element + " processed by normal stream: " + Thread.currentThread().getName()));

        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        forkJoinPool
                .submit(() -> {
                    stringList
                            .parallelStream()
                            .forEach(element -> System.out.println("Element: " + element + " processed by custom fork join pool: " + Thread.currentThread().getName()));
                }).get();

        forkJoinPool.shutdown();
    }
}
