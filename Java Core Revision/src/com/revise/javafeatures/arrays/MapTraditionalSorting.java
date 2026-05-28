package com.revise.javafeatures.arrays;

import java.util.*;

public class MapTraditionalSorting {
    public static void main(String[] args) {
        Map<String, Integer> stringIntegerMap = Map.ofEntries(
                Map.entry("hello", 100),
                Map.entry("hi", 200),
                Map.entry("hey", 500)
        );
        System.out.println("Map before sorting: " + stringIntegerMap);
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        //Sort stringIntegerMap without using streams API:
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(stringIntegerMap.entrySet());
        System.out.println("Entry list before sorting: " + entryList);
        Collections
                .sort(entryList, (entryFirst, entrySecond) -> {
                    if (entryFirst.getValue() > entrySecond.getValue()) {
                        return 1;
                    } else if (entryFirst.getValue() < entrySecond.getValue()) {
                        return -1;
                    } else {
                        return 0;
                    }
                });
        System.out.println("Entry list after sorting: " + entryList);

        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        System.out.println("Map after sorting: " + sortedMap);
    }

}
