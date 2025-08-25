package com.mycollections;

import java.util.*;

public class CollectionUtility {
    public static void main(String[] args) {
        List<String> playerList = Arrays.asList("Sanju", "Surya", "Virat", "Rohit", "Axar Patel", "Bumrah");
        System.out.println("Player list without sorting: ");
        System.out.println(playerList);


        //Natural ordering:
        //Collections.sort(playerList);
        //System.out.println("Player list after sorting in natural order: ");
        //System.out.println(playerList);


        //Sorting according to name length:
       /* Collections.sort(playerList, Comparator.comparingInt((string) -> {
            return string.length();
        }));
        System.out.println("Player list sorted according to name length: ");
        System.out.println(playerList);
*/

        //sort in descending order.
        /*Collections.sort(playerList, Comparator.reverseOrder());
        System.out.println("Player list sorted in descending order");
        System.out.println(playerList);*/


        //reverse list
        /*Collections.reverse(playerList);
        System.out.println("Player list in reverse order: ");
        System.out.println(playerList);
*/


        //shuffle list
       /* Collections.shuffle(playerList);
        System.out.println("Player list shuffled: ");
        System.out.println(playerList);*/


        //perform binary search:
        /*Collections.sort(playerList);
        System.out.println("Player list after sorting: ");
        System.out.println(playerList);
        int indexOfSearchedElement = Collections.binarySearch(playerList, "Axar Patel");
        System.out.println("Index of the searched element: " + indexOfSearchedElement);*/


        //swap of the elements:
       /* Collections.swap(playerList, 0, 3);
        System.out.println("Player list after swap of elements: ");
        System.out.println(playerList);*/


        //unmodifiable list: add, set get nothing can be done.
       /* List<String> stringList = Collections.unmodifiableList(playerList);
        stringList.set(0, "ABC");*/


        //find longest and shortest names.
        /*String longestName = Collections.max(playerList, Comparator.comparingInt((string) -> {
            return string.length();
        }));
        System.out.println("Longest name: " + longestName);

        String shortestName = Collections.min(playerList, Comparator.comparingInt((string) -> {
            return string.length();
        }));
        System.out.println("Shortest name: " + shortestName);
*/
        //element frequency:
        /*int elementFrequency = Collections.frequency(playerList, "Sanju");
        System.out.println("Frequency of element: " + elementFrequency);*/

        //copying list.
       /* List<String> copyList = new ArrayList<>(Collections.nCopies(playerList.size(), ""));
        System.out.println("copyList size: " + copyList.size());
        Collections.copy(copyList, playerList);
        System.out.println("Log copied list: " + copyList);*/


        //replacing all elements with a single value:
        /*Collections.fill(playerList, "test player");
        System.out.println("Player list after using fill method: ");
        System.out.println(playerList);*/

        //synchronized collection:
        List<String> synchronizedPlayerList = Collections.synchronizedList(playerList);
        System.out.println("Synchronized player list: ");
        System.out.println(synchronizedPlayerList);
    }
}
