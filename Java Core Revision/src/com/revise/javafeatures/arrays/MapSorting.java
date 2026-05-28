package com.revise.javafeatures.arrays;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSorting {
    public static void main(String[] args) {
        Map<String, Integer> myMap = Map.of(
                "hello", 100,
                "hi", 100,
                "heythere", 200,
                "geeks", 500
        );
        //Sort map by value:
        LinkedHashMap<String, Integer> mapSortedByValue = myMap
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getValue()))
                .collect(Collectors.toMap(myEntry -> myEntry.getKey(), valueEntry -> valueEntry.getValue(), (v1, v2) -> v2, () -> new LinkedHashMap<String, Integer>()));

        System.out.println("Map before sorting by value: " + myMap);
        System.out.println("Map after sorting by value: " + mapSortedByValue);

        LinkedHashMap<String, Integer> mapSortedByKey = myMap
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getKey()))
                .collect(Collectors.toMap(keyEntry -> keyEntry.getKey(), valueEntry -> valueEntry.getValue(), (v1, v2) -> v1, () -> new LinkedHashMap<String, Integer>()));
        System.out.println("Map before sorting by key: " + myMap);
        System.out.println("Map after sorting by key: " + mapSortedByKey);

        //If in sorting break ties using thenComparing:
        LinkedHashMap<String, Integer> sortMapByValueAndBrokeTiesUsingThenComparing = myMap
                .entrySet()
                .stream()
                .sorted(Comparator.comparing((Map.Entry<String, Integer> entry) -> entry.getValue()).thenComparing((Map.Entry<String, Integer> entry) -> entry.getKey()))
                .collect(Collectors.toMap(myEntryKey -> myEntryKey.getKey(), myEntryValue -> myEntryValue.getValue(), (valueOld, valueNew) -> valueNew, () -> new LinkedHashMap<String, Integer>()));

        System.out.println("Map before sorting by value and then comparing: " + myMap);
        System.out.println("Map after sorting by value and ties by key: " + sortMapByValueAndBrokeTiesUsingThenComparing);


        //Ties cannot happen anytime for the key because key will get overridden at the time
        //of map creation
        Map<String, Integer> stringIntegerMap = Map.ofEntries(
                Map.entry("samarth", 100),
                Map.entry("infosys", 200)
        );
        System.out.println("String Integer map: " + stringIntegerMap);

    }
}
