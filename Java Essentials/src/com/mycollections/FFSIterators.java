package com.mycollections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FFSIterators {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);

        Iterator<Integer> iterator = integerList.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            System.out.println("Element from list: " + element);
            integerList.add(100);
        }

        for (Integer element : integerList) {
            System.out.println("Log element from the list: " + element);
            //integerList.add(100);
        }

    }
}
