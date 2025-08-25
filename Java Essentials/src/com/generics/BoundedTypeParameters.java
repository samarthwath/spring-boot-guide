package com.generics;

import java.util.List;

public class BoundedTypeParameters {

    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Double> doubleList = List.of(1.1, 2.2, 3.3, 4.4, 5.5);
        /*findMax(integerList);
        findMax(doubleList);*/

        findMaxSecond(integerList);
        findMaxSecond(doubleList);

        printWildCardList(List.of("hello", "world", "Jo", "John"));
        printList(List.of(100, 22, 33, 44, 55, 434));
    }

    //With wild card it always treat the type parameter as object.
    //This approach is less type safe.
    private static void printWildCardList(List<?> itemList) {
        System.out.println("printWildCardList method");
        for (Object item : itemList) {
            System.out.println("Item: " + item);
        }
    }

    private static <T> void printList(List<T> items) {
        System.out.println("printList method: ");
        for (T item : items) {
            System.out.println("Print item: " + item);
        }
    }

    //Bounded type parameters.
    private static <T extends Integer> void findMax(List<T> list) {
        T max = list.get(0);
        for (T element : list) {
            //compareTo method belongs to Comparable interface. The type should implement
            //that interface.
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        System.out.println("max element: " + max);
    }

    //multiple type bounded parameters.
    private static <T extends Number & Comparable<T>> void findMaxSecond(List<T> list) {
        T max = list.get(0);
        for (T element : list) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        System.out.println("max element: " + max);
    }
}
