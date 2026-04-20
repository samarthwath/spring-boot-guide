package com.revise.javafeatures;

import java.util.*;

public class JavaStreams {

    public static void main(String[] args) {
        Map<String, String> helloMap = Map.of("hello", "Hi", "hey", "there", "hi", "How are you");

        //Not the ideal way as it requires extra look up into the map
        helloMap
                .keySet()
                .stream()
                .forEach(key -> System.out.println(key + ":" + helloMap.get(key)));

        helloMap
                .entrySet()
                .stream()
                .forEach(System.out::println);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Set<Integer> integers1 = new HashSet<>(integers);

    }


}
