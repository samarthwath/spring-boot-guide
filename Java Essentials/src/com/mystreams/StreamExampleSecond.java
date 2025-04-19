package com.mystreams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreamExampleSecond {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);

        //Find sum of squares of even numbers.

        Optional<Integer> reducedValue = integerList.stream().filter((value) -> {
            return value % 2 == 0;
        }).map((value) -> {
            return value * value;
        }).reduce((valueFirst, valueSecond) -> {
            return valueFirst + valueSecond;
        });
        System.out.println("Log sum of squares of even numbers: ");
        System.out.println(reducedValue.get());

        Optional<Integer> reducedAnswer = integerList.stream().filter(value -> value % 2 == 0).map(value -> value * value).reduce((valueFirst, valueSecond) -> valueFirst + valueSecond);
        System.out.println("Log reducedAnswer: " + reducedAnswer.get());
    }
}
