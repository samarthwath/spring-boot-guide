package com.mystreams;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExampleFirst {
    public static void main(String[] args) {
        //Primitive Type array.
        int[] arr = {1, 2, 3, 4, 5};
        IntStream intArrayStream = Arrays.stream(arr);
        System.out.println("Log stream which we created from primitive type: ");
        intArrayStream.forEach((value) -> {
            System.out.println(value);
        });

        //Object Type array.
        Integer[] arrIntObject = {1, 2, 3, 4, 5};
        Stream<Integer> arrIntObjectStream = Stream.of(arrIntObject);
        System.out.println("Log stream which we created from the object type: ");
        arrIntObjectStream.forEach((value) -> {
            System.out.println(value);
        });
    }
}
