package com.mystreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapVsFlatMap {
    public static void main(String[] args) {
        //Map Use case:
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        stringList.add("World");
        System.out.println("Log original stringList: ");
        System.out.println(stringList);
        List<String> stringListUpperCased = stringList.stream().map(value -> value.toUpperCase()).collect(Collectors.toList());
        System.out.println("Log string list upper cased: ");
        System.out.println(stringListUpperCased);
        //FlatMap Use case:
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("Hello", "World", "Awesome"),
                Arrays.asList("Geeks", "for", "Geeks"),
                Arrays.asList("you", "are", "powerful")
        );
        System.out.println("Log nested list before: ");
        System.out.println(nestedList);
        List<String> uppercasedNestListOutputWithFlatMap = nestedList.stream().flatMap(list -> list.stream()).map(value -> value.toUpperCase()).collect(Collectors.toList());
        System.out.println("Log upper cased list output with flat map: ");
        System.out.println(uppercasedNestListOutputWithFlatMap);
    }

}
