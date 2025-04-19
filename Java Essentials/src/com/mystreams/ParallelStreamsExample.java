package com.mystreams;

import java.util.Arrays;

public class ParallelStreamsExample {
    public static void main(String[] args) {
        int[] arr = new int[1000000];
        Arrays.fill(arr, 2);
        //Calculate sum of squares using sequential stream.
//        long startTime = System.currentTimeMillis();
//        int sum = Arrays.stream(arr)
//                .map(value -> value * value)
//                .sum();
//        System.out.println("Sum value with sequential stream: " + sum);
//        long endTime = System.currentTimeMillis();
//
//        System.out.println("Time required with sequential stream: ");
//        System.out.println(endTime - startTime);

        long parallelStartTime = System.currentTimeMillis();
        int sumWithParallelStream = Arrays.stream(arr)
                .parallel()
                .map(value -> value * value)
                .sum();
        System.out.println("Sum value with parallel stream: " + sumWithParallelStream);
        long parallelEndTime = System.currentTimeMillis();
        System.out.println("Time required with parallel stream: ");
        System.out.println(parallelEndTime - parallelStartTime);
    }
}
