package com.revise.javafeatures.arrays;

import java.util.*;

public class ArrayFirst {
    public static void main(String[] args) {

        //Find the largest element from the array:
        //Brute Force: Sort Array + Get the last element O(nlogn)
        //Below is the better solution: Time Complexity O(n)
        Integer arr[] = {0, 4, 3, 5, 1};
        Integer sortedArr[] = {1, 2, 2, 3, 4, 5, 5, 6};
        int largestElement = arr[0];
        for (Integer element : arr) {
            if (element > largestElement) {
                largestElement = element;
            }
        }
        System.out.println("Largest element better/optimal approach: " + largestElement);

        //Second Largest Element:
        //Below is the Brute force approach:
        Arrays.sort(arr); //nlogn
        Integer bruteforceLargest = arr[arr.length - 1];
        System.out.println("Largest element brute force approach: " + bruteforceLargest);
        int bruteForceSecondLargest = Integer.MIN_VALUE;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] < largestElement) {
                bruteForceSecondLargest = arr[i];
                break;
            }
        }
        System.out.println("Brute force second largest: " + bruteForceSecondLargest);


        //Below is the Better Approach: O(2n)
        int secondLargest = Integer.MIN_VALUE;
        int firstLargest = Integer.MIN_VALUE;
        for (Integer element : arr) {
            if (element > firstLargest) {
                firstLargest = element;
            }
        }
        for (Integer element : arr) {
            if (element < firstLargest && element > secondLargest) {
                secondLargest = element;
            }
        }
        System.out.println("Second Largest better approach: " + secondLargest);


        //Second largest element optimal approach:
        //0, 4, 3, 5, 1
        Integer[] unsorted = {0, 4, 3};
        int fLargest = Integer.MIN_VALUE;
        int sLargest = Integer.MIN_VALUE;
        for (Integer element : unsorted) {
            if (element > fLargest) {
                sLargest = fLargest;
                fLargest = element;
            } else if (element > sLargest) {
                sLargest = element;
            }
        }
        System.out.println("FirstLarest Optimal approach: " + fLargest);
        System.out.println("SecondLargest Optimal approach: " + sLargest);

        //Check if the array is sorted
        boolean isArraySorted = true;
        for (int i = 0; i < sortedArr.length; i++) {
            if (i <= sortedArr.length - 2 && sortedArr[i] > sortedArr[i + 1]) {
                isArraySorted = false;
                break;
            }
        }
        System.out.println("Is array sorted: " + isArraySorted);

        //Remove duplicates from Sorted array:
        //Below is the brute force approach:
        Set<Integer> elementSet = new HashSet<>();
        for (Integer element : sortedArr) {
            elementSet.add(element);
        }
        int sortedArrayIterator = 0;
        for (Integer setElement : elementSet) {
            sortedArr[sortedArrayIterator] = setElement;
            sortedArrayIterator++;
        }
        System.out.println("Unique elements: " + elementSet.size());
        System.out.println("Sorted Array after removing duplicates in place: " + Arrays.toString(sortedArr));

        //Below is the better approach:
        Integer[] newSortedArr = {1, 2, 2, 3, 4, 5, 5, 6};
        int firstPointer = 0;
        for (int j = 1; j < newSortedArr.length; j++) {
            if (newSortedArr[j] != newSortedArr[firstPointer]) {
                newSortedArr[firstPointer + 1] = newSortedArr[j];
                firstPointer++;
            }
        }
        int totalUniqueElements = firstPointer + 1;
        System.out.println("Sorted Array after removing duplicates in place: " + Arrays.toString(newSortedArr));
        System.out.println("Unique elements: " + totalUniqueElements);


        //Left rotate array by one place:
        Integer newArray[] = {1, 2, 3, 4, 5, 6};
        System.out.println("Array Before left rotation by x place: " + Arrays.toString(newArray));
//        int firstElement = newArray[0];
//        for (int i = 0; i < newArray.length - 1; i++) {
//            newArray[i] = newArray[i + 1];
//        }
//        newArray[newArray.length - 1] = firstElement;
//        System.out.println("Array After left rotation by 1 place: " + Arrays.toString(newArray));


        //Left rotate array by x places:
        Set<Integer> lInitials = new HashSet<>();
        int places = 2;
        int leftIterator = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (i < places) {
                lInitials.add(newArray[i]);
            } else {
                newArray[i - places] = newArray[i];
                leftIterator++;
            }
        }
        for (Integer element : lInitials) {
            newArray[leftIterator] = element;
            leftIterator++;
        }
        System.out.println("Left Rotation of Array by x places: " + Arrays.toString(newArray));

        //Right Rotate by x places:
        Integer[] newRightArray = {1, 2, 3, 4, 5, 6};
        System.out.println("Array before right  rotation by x place: " + Arrays.toString(newRightArray));
        Set<Integer> rInitials = new HashSet<>();
        int rPlaces = 3;
        int rightIterator = 0;
        for (int i = newRightArray.length - 1; i >= 0; i--) {
            if (i >= newRightArray.length - rPlaces) {
                rInitials.add(newRightArray[i]);
            } else {
                newRightArray[i + rPlaces] = newRightArray[i];
            }
        }
        for (Integer element : rInitials) {
            newRightArray[rightIterator] = element;
            rightIterator++;
        }
        System.out.println("Right Rotation of Array by x places: " + Arrays.toString(newRightArray));

        Integer[] arrayWithZeros = {1, 2, 0, 3, 4, 0, 0, 8, 7, 5, 6, 0};
        System.out.println("Array with zeros before: " + Arrays.toString(arrayWithZeros));
        //Move zeros to the end of the array:
        List<Integer> nonZeros = new ArrayList<>();
        for (Integer element : arrayWithZeros) {
            if (element != 0) {
                nonZeros.add(element);
            }
        }
        for (int index = 0; index < arrayWithZeros.length; index++) {
            if (index < nonZeros.size()) {
                arrayWithZeros[index] = nonZeros.get(index);
            } else {
                arrayWithZeros[index] = 0;
            }
        }
        System.out.println("Array with zeros after: " + Arrays.toString(arrayWithZeros));


        Integer[] newArrayWithZeros = {1, 2, 0, 3, 4, 0, 0, 8, 7, 5, 6, 0};
        System.out.println("Array with zeros before better approach: " + Arrays.toString(newArrayWithZeros));
        int mFp = -1;
        for (int i = 0; i < newArrayWithZeros.length; i++) {
            if (newArrayWithZeros[i] == 0) {
                mFp = i;
                break;
            }
        }
        //Above for loop will give index of 1st zeroth element:
        for (int i = mFp + 1; i < newArrayWithZeros.length; i++) {
            if (newArrayWithZeros[i] != 0) {
                //swap elements
                int temp = newArrayWithZeros[mFp];
                newArrayWithZeros[mFp] = newArrayWithZeros[i];
                newArrayWithZeros[i] = temp;
                //increement mFp
                mFp++;
            }
        }
        System.out.println("Array with zeros after better approach: " + Arrays.toString(newArrayWithZeros));

        //Find first and last position of target element in sorted array:
        //If all occurrences of target element is required we can store it in some DS.
        Integer[] myNewSortedArray = {1, 2, 2, 3, 4, 5, 5, 6};
        int targetElement = 2;
        int firstPosition = -1;
        int lastPosition = -1;
        for (int i = 0; i < myNewSortedArray.length; i++) {
            if (myNewSortedArray[i] == targetElement) {
                firstPosition = i;
                break;
            }
        }
        for (int i = myNewSortedArray.length - 1; i >= 0; i--) {
            if (myNewSortedArray[i] == targetElement) {
                lastPosition = i;
                break;
            }
        }
        System.out.println("First & Last Position of target element: " + firstPosition + " " + lastPosition);

        //Union and intersection of two sorted arrays:
        Integer[] arrayFirst = {1, 1, 2, 3, 4, 5};
        Integer[] arraySecond = {2, 3, 4, 4, 5};
        System.out.println("Array first: " + Arrays.toString(arrayFirst));
        System.out.println("Array second: " + Arrays.toString(arraySecond));
        //Union Brute force approach:
        Set<Integer> unionSet = new HashSet<>();
        Set<Integer> integersSet = new HashSet<>();
        Set<Integer> intersectionSet = new HashSet<>();
        for (Integer element : arrayFirst) {
            unionSet.add(element);
            integersSet.add(element);
        }
        for (Integer element : arraySecond) {
            unionSet.add(element);
            if (integersSet.contains(element)) {
                intersectionSet.add(element);
            }
        }
        Integer[] unionArray = new Integer[unionSet.size()];
        Integer[] intersectionArray = new Integer[intersectionSet.size()];
        int unionIterator = 0;
        int intersectionIterator = 0;
        for (Integer element : unionSet) {
            unionArray[unionIterator] = element;
            unionIterator++;
        }
        for (Integer element : intersectionSet) {
            intersectionArray[intersectionIterator] = element;
            intersectionIterator++;
        }

        System.out.println("Union Array: " + Arrays.toString(unionArray));
        System.out.println("Intersection Array: " + Arrays.toString(intersectionArray));

        //Find missing number in the array from 1 to N:
        //Below is the Brute force approach:
        Integer[] numberMissing = {1, 2, 4, 5};
        int N = 5;
        int missingNumber = -1;
        boolean isNumberMissing;
        for (int iterator = 1; iterator <= N; iterator++) {
            isNumberMissing = true;
            for (int j = 0; j < numberMissing.length; j++) {
                if (numberMissing[j] == iterator) {
                    isNumberMissing = false;
                    break;
                }
            }
            if (isNumberMissing) {
                missingNumber = iterator;
                break;
            }
        }
        System.out.println("Missing Number from brute force approach: " + missingNumber);

        //Missing number from better approach by using extra DS like Set, List:
        List<Integer> integerList = new ArrayList<>();
        int missingNumberBetter = -1;
        for (Integer element : numberMissing) {
            integerList.add(element);
        }
        for (int i = 1; i <= N; i++) {
            if (!integerList.contains(i)) {
                missingNumberBetter = i;
                break;
            }
        }
        System.out.println("Missing number better approach: " + missingNumberBetter);
        int missingNumberOptimal = -1;
        int expectedSum = N * (N + 1) / 2;
        int actualSum = 0;
        for (int i = 0; i < numberMissing.length; i++) {
            actualSum = actualSum + numberMissing[i];
        }
        missingNumberOptimal = expectedSum - actualSum;
        System.out.println("Missing number optimal: " + missingNumberOptimal);

        Integer[] zerosOnesArray = {1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1};
        //Find maximum consecutive ones:
        int onesCounter = 0;
        int maxOnes = 0;
        for (int i = 0; i < zerosOnesArray.length; i++) {
            if (zerosOnesArray[i] == 1) {
                onesCounter++;
                if (onesCounter > maxOnes) {
                    maxOnes = onesCounter;
                }
            } else if (zerosOnesArray[i] == 0) {
                onesCounter = 0;
            }
        }
        System.out.println("Max ones: " + maxOnes);
        Integer[] myArray = {1, 1, 2, 2, 3, 3, 4, 4, 5};
        int numberWhichAppearedOnce = 0;
        int numberAppearanceCount = 0;
        //Find the number which appears once whereas other number appears twice
        for (int iterator = 0; iterator < myArray.length; iterator++) {
            numberAppearanceCount = 0;
            for (int jIterator = 0; jIterator < myArray.length; jIterator++) {
                if (myArray[jIterator] == myArray[iterator]) {
                    numberAppearanceCount++;
                }
            }
            if (numberAppearanceCount == 1) {
                numberWhichAppearedOnce = myArray[iterator];
                break;
            }
        }
        System.out.println("Number which appeared once brute force: " + numberWhichAppearedOnce);

        //Better Approach:
        Map<Integer, Integer> elementsMap = new HashMap<>();
        int numberAppearedOnceBetter = -1;
        for (int iterator = 0; iterator < myArray.length; iterator++) {
            if (elementsMap.containsKey(myArray[iterator])) {
                elementsMap.put(myArray[iterator], elementsMap.get(myArray[iterator]) + 1);
            } else {
                elementsMap.put(myArray[iterator], 1);
            }
        }
        for (Integer key : elementsMap.keySet()) {
            if (elementsMap.get(key) == 1) {
                numberAppearedOnceBetter = key;
                break;
            }
        }
        System.out.println("Number appeared once with better approach: " + numberAppearedOnceBetter);
    }

}
