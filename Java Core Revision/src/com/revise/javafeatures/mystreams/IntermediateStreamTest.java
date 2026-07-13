package com.revise.javafeatures.mystreams;

import java.util.*;
import java.util.stream.*;

public class IntermediateStreamTest {
    public static void main(String[] args) {
        String str = "bbssamarth";
        //Find first non-repeating character:
        Character firstNonRepeatingCharacter = str
                .chars()
                .mapToObj(charValue -> (char) charValue)
                .collect(Collectors.groupingBy(character -> character, () -> new LinkedHashMap<Character, Long>(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entryKey -> entryKey.getKey())
                .findFirst()
                .orElse(null);
        System.out.println("First non repeating character: " + firstNonRepeatingCharacter);

        //Frequency of each character:
        Map<Character, Long> characterFrequencyMap = str
                .chars()
                .mapToObj(charValue -> (char) charValue)
                .collect(Collectors.groupingBy(character -> character, () -> new HashMap<>(), Collectors.counting()));
        System.out.println(characterFrequencyMap);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //second highest number in a list:
        Integer secondHighestElement = numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println(secondHighestElement);

        Integer highestElement = numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(null);
        System.out.println(highestElement);

        Integer minimumElement = numbers
                .stream()
                .sorted()
                .findFirst()
                .orElse(null);
        System.out.println(minimumElement);

        Integer secondMinimumElement = numbers
                .stream()
                .distinct()
                .sorted(Comparator.naturalOrder())
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println(secondMinimumElement);


        List<Integer> duplicateNumbers = List.of(9, 9, 2, 1, 1, 23, 23);
        Set<Integer> duplicateSet = new HashSet<>();
        List<Integer> duplicates = duplicateNumbers
                .stream()
                .filter(element -> !duplicateSet.add(element))
                .toList();
        System.out.println(duplicates);

        Set<Integer> anyDuplicates = new HashSet<>();
        //List contains any duplicate element:
        boolean isAnyDuplicate = duplicateNumbers
                .stream()
                .anyMatch(element -> !anyDuplicates.add(element));
        System.out.println(isAnyDuplicate);

        List<Integer> newDuplicates = List.of(9, 9, 2, 2, 1, 1, 23, 23);
        Set<Integer> allDuplicates = new HashSet<>();
        boolean areAllDuplicates = newDuplicates
                .stream()
                .allMatch(elementValue -> !allDuplicates.add(elementValue));
        System.out.println("Are duplicates: " + areAllDuplicates);

        List<String> strings = List.of("def", "abc", "ijk", "lmnopqrstuvwxyz");
        List<String> stringsAscendingSorted = strings
                .stream()
                .sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()))
                .toList();
        System.out.println(stringsAscendingSorted);

        Set<Integer> countDuplicates = new HashSet<>();
        long duplicatesCount = newDuplicates
                .stream()
                .filter(element -> !countDuplicates.add(element))
                .count();
        System.out.println(duplicatesCount);

        //Remove duplicates while preserving the order:
        List<Integer> removedDuplicatesList = newDuplicates
                .stream()
                .distinct()
                .toList();
        System.out.println(removedDuplicatesList);

        List<Integer> topThreeHighestNumberList = newDuplicates
                .stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .sorted(Comparator.naturalOrder())
                .toList();
        System.out.println(topThreeHighestNumberList);

        //Longest string in a list:
        String longestStringFromList = strings
                .stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .findFirst()
                .orElse(null);
        System.out.println(longestStringFromList);

        //Partition numbers into even and odd:
        Map<Boolean, List<Integer>> partitionedMap = newDuplicates
                .stream()
                .distinct()
                .collect(Collectors.partitioningBy(element -> element % 2 == 0));
        System.out.println(partitionedMap);


        //Group words according to there length:
        Map<Integer, List<String>> groupWordsAccordingToLength = strings
                .stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(groupWordsAccordingToLength);

        //List of string converted to map:
        Map<String, Integer> stringListToMap = strings
                .stream()
                .collect(Collectors.toMap(string -> string, string -> string.length()));
        System.out.println(stringListToMap);

        //List of string converted to map:
        Map<Integer, String> stringListToNewMap = strings
                .stream()
                .collect(Collectors.toMap(string -> string.length(), string -> string, (oldValue, newValue) -> newValue, () -> new HashMap<>()));
        System.out.println(stringListToNewMap);

        List<String> stringList = List.of("abc", "abcd", "abcde", "abcdef", "abcdefg", "abcdefgh");
        Map<String, Integer> sortedMapByValueDescending = stringList
                .stream()
                .collect(Collectors.toMap(string -> string, string -> string.length(), (oldValue, newValue) -> newValue))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing((Map.Entry<String, Integer> entry) -> entry.getKey()).reversed())
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (oldValue, newValue) -> newValue, () -> new LinkedHashMap<>()));

        System.out.println(sortedMapByValueDescending);


    }

}
