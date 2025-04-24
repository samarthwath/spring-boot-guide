package arrayquestions;

import java.util.*;

public class ArrayQuestions {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 43, 342};
        int[] unsortedArray = {1, 2, 3, 5, 43, 342};
        int[] duplicatesSortedArray = {1, 1, 2, 2, 3, 33, 33, 44, 44};
        int[] leftRotateArray = {1, 2, 3, 4, 5};
        int[] rightRotateArray = {1, 2, 3, 4, 5};
        int[] rightRotateArr = {1, 2, 3, 4, 5};
        int[] kRightRotateArr = {1, 2, 3, 4, 5};
        int[] kLeftRotateArr = {1, 2, 3, 4, 5};
        int[] zerosArray = {1, 0, 2, 3, 2, 0, 0, 4, 5, 1};
        int[] firstArray = {1, 1, 2, 3, 4, 5};
        int[] secondArray = {3, 4, 5, 6};
        int[] missingNumberArray = {1, 2, 4, 5};
        int[] onesArray = {1, 1, 0, 1, 1, 1, 0, 1, 1, 1};
        int[] numAppearsOnce = {1, 1, 2, 3, 3, 4, 4};
        System.out.println("Largest element from the array from Better Approach : " + largestElementOfArrayBetterApproach(arr));
        System.out.println("Largest element from the array Brute Force Approach : " + largestElementOfArrayBruteForce(arr));
        System.out.println("Second Largest element from the Brute force Approach: " + secondLargestElementOfArrayBruteForceAppraoch(arr));
        System.out.println("Second Largest element from the array: " + secondLargestElementOfArrayBetterApproach(arr));
        System.out.println("Second Largest element from the array with better approach: " + secondLargestElementOfArrayOptimalApproach(arr));
        System.out.println("Check if the array is sorted: " + checkIfTheArrayIsSorted(unsortedArray));
        removeDuplicatesFromSortedArray(duplicatesSortedArray);
        leftRotateArrayByOnePlace(leftRotateArray);
        rightRotateArrayByOnePlace(rightRotateArray);
        rightRotateArrayByTwoPlaces(rightRotateArr, 2);
//        rightRotateArrayByKPlaces(kRightRotateArr, 3);
        leftRotateArrayByKPlaces(kLeftRotateArr, 2);
        moveZerosToTheEnd(zerosArray);
        unionAndIntersectionOfTwoArrays(firstArray, secondArray);
        findMissingNumberBruteForce(missingNumberArray, 5);
        findMissingNumberOptimalApproach(missingNumberArray, 5);
        maxConsecutiveOnes(onesArray);
        findTheNumberWhichAppearsOnce(numAppearsOnce);
    }

    private static void findTheNumberWhichAppearsOnce(int[] numAppearsOnce) {
        int numberValueWhichAppearsOnce = -1;
        int numberCounter=0;
        for (int iterator = 0; iterator < numAppearsOnce.length; iterator++) {
            numberCounter=0;
            for (int searchIndex=0; searchIndex<numAppearsOnce.length; searchIndex++) {
                if(numAppearsOnce[iterator]==numAppearsOnce[searchIndex]){
                    numberCounter++;
                }
            }
            if(numberCounter==1){
                numberValueWhichAppearsOnce=numAppearsOnce[iterator];
                break;
            }
        }
        System.out.println("Log Number which appears once: " + numberValueWhichAppearsOnce);
    }

    private static void maxConsecutiveOnes(int[] onesArray) {
        int onesCounter = 0;
        int maximumOnes = 0;
        for (int index = 0; index < onesArray.length; index++) {
            if (onesArray[index] != 0) {
                onesCounter++;
            } else if (onesCounter > maximumOnes) {
                maximumOnes = onesCounter;
                onesCounter = 0;
            }
        }
        System.out.println("Maximum number of consecutive one's from the array: " + maximumOnes);
    }

    private static void findMissingNumberOptimalApproach(int[] missingNumberArray, int nValue) {
        int totalExpectedSum = (nValue * (nValue + 1)) / 2;
        int actualSum = 0;
        for (int index = 0; index < missingNumberArray.length; index++) {
            actualSum = actualSum + missingNumberArray[index];
        }
        int difference = totalExpectedSum - actualSum;
        System.out.println("Missing Number: " + difference);
    }

    private static void findMissingNumberBruteForce(int[] missingNumberArray, int n) {
        int missingNumber = -1;
        for (int numValue = 1; numValue <= n; numValue++) {
            boolean isMissingNumber = true;
            for (int index = 0; index < missingNumberArray.length; index++) {
                if (numValue == missingNumberArray[index]) {
                    isMissingNumber = false;
                }
            }
            if (isMissingNumber) {
                missingNumber = numValue;
                break;
            }
        }
        System.out.println("Printing missingNumber: " + missingNumber);
    }

    private static void unionAndIntersectionOfTwoArrays(int[] firstArray, int[] secondArray) {
        Set<Integer> intersectedSet = new HashSet<>();
        List<Integer> intersectedList = new ArrayList<>();
        Set<Integer> unionSet = new HashSet<>();
        for (int index = 0; index < firstArray.length; index++) {
            unionSet.add(firstArray[index]);
            intersectedSet.add(firstArray[index]);
        }
        for (int index = 0; index < secondArray.length; index++) {
            unionSet.add(secondArray[index]);
            if (intersectedSet.contains(secondArray[index])) {
                intersectedList.add(secondArray[index]);
            }
        }
        System.out.println("Log intersectedList: " + intersectedList);
        System.out.println("Log unionList: " + unionSet);
    }

    private static void moveZerosToTheEnd(int[] zerosArray) {
        int[] arrNew = new int[zerosArray.length];
        int indexPointer = 0;
        System.out.println("Log zerosArray before: ");
        Arrays.stream(zerosArray).forEach(element -> System.out.print(element + "\t"));
        System.out.println();
        for (int index = 0; index < zerosArray.length; index++) {
            if (zerosArray[index] != 0) {
                arrNew[indexPointer] = zerosArray[index];
                indexPointer++;
            }
        }
        for (int index = 0; index < zerosArray.length; index++) {
            zerosArray[index] = arrNew[index];
        }
        System.out.println("Log zerosArray after: ");
        Arrays.stream(zerosArray).forEach(element -> System.out.print(element + "\t"));
    }

    private static void leftRotateArrayByKPlaces(int[] kLeftRotateArr, int places) {
        System.out.println("Log original array before left rotation: ");
        Arrays.stream(kLeftRotateArr).forEach(element -> System.out.print(element + "\t"));
        System.out.println();
        List<Integer> intList = new ArrayList<>();
        for (int iterator = 0; iterator < kLeftRotateArr.length; iterator++) {
            if (iterator < places) {
                intList.add(kLeftRotateArr[iterator]);
            } else {
                kLeftRotateArr[iterator - places] = kLeftRotateArr[iterator];
            }
        }
        int startIndex = kLeftRotateArr.length - places;
        for (int index = 0; index < intList.size(); index++) {
            kLeftRotateArr[startIndex] = intList.get(index);
            startIndex++;
        }
        System.out.println("Log original array after left rotation: ");
        Arrays.stream(kLeftRotateArr).forEach(element -> System.out.print(element + "\t"));
        System.out.println();
    }

    private static void rightRotateArrayByTwoPlaces(int[] rightRotateArr, int places) {
        System.out.println("Log original rightRotateArray: ");
        Arrays.stream(rightRotateArr).forEach(element -> System.out.print(element + "\t"));
        System.out.println();
        List<Integer> intList = new ArrayList<>();
        for (int index = rightRotateArr.length - 1; index >= 0; index--) {
            if (index > places) {
                intList.add(rightRotateArr[index]);
            } else {
                rightRotateArr[index + 2] = rightRotateArr[index];
            }
        }
        for (int index = 0; index < intList.size(); index++) {
            rightRotateArr[places - 1] = intList.get(index);
            places--;
        }
        System.out.println("Log rightRotateArray after places: " + places);
        Arrays.stream(rightRotateArr).forEach(element -> System.out.print(element + "\t"));
        System.out.println();
    }

    private static void rightRotateArrayByKPlaces(int[] rightRotateArr, int places) {
        System.out.println("Log original rightRotateArray: ");
        Arrays.stream(rightRotateArr).forEach(element -> System.out.print(element + "\t"));
        System.out.println();
        List<Integer> intList = new ArrayList<>();
        for (int index = rightRotateArr.length - 1; index >= 0; index--) {
            if (index >= places) {
                intList.add(rightRotateArr[index]);
            } else {
                rightRotateArr[index + places] = rightRotateArr[index];
            }
        }
        for (int index = 0; index < intList.size(); index++) {
            rightRotateArr[places - 1] = intList.get(index);
            places--;
        }
        System.out.println("Log rightRotateArray after places: " + places);
        Arrays.stream(rightRotateArr).forEach(element -> System.out.print(element + "\t"));
        System.out.println();
    }


    private static void rightRotateArrayByOnePlace(int[] rightRotateArray) {
        System.out.println("Log original array before right rotation: ");
        Arrays.stream(rightRotateArray).forEach(element -> System.out.print(element + "\t"));
        int firstElement = 0;
        for (int index = rightRotateArray.length - 1; index >= 0; index--) {
            if (index == rightRotateArray.length - 1) {
                firstElement = rightRotateArray[index];
                rightRotateArray[index] = rightRotateArray[index - 1];
            } else if (index == 0) {
                rightRotateArray[index] = firstElement;
            } else {
                rightRotateArray[index] = rightRotateArray[index - 1];
            }
        }
        System.out.println("Log original array after right rotation: ");
        Arrays.stream(rightRotateArray).forEach(element -> System.out.print(element + "\t"));
    }

    private static void leftRotateArrayByOnePlace(int[] leftRotateArray) {
        System.out.println("Log originalArray: ");
        int lastElement = 0;
        Arrays.stream(leftRotateArray).forEach(element -> System.out.print(element + "\t"));
        for (int index = 0; index < leftRotateArray.length; index++) {
            if (index == 0) {
                lastElement = leftRotateArray[index];
                leftRotateArray[index] = leftRotateArray[index + 1];
            } else if (index == leftRotateArray.length - 1) {
                leftRotateArray[index] = lastElement;
            } else {
                leftRotateArray[index] = leftRotateArray[index + 1];
            }
        }
        System.out.println("Log Left Rotated Array: ");
        Arrays.stream(leftRotateArray).forEach(element -> System.out.print(element + "\t"));

    }

    private static void removeDuplicatesFromSortedArray(int[] duplicatesSortedArray) {
        int[] cleanedArray = new int[duplicatesSortedArray.length];
        Set<Integer> noDuplicatesSet = new LinkedHashSet<>();
        int iterator = 0;
        for (int index = 0; index < duplicatesSortedArray.length; index++) {
            noDuplicatesSet.add(duplicatesSortedArray[index]);
        }
        System.out.println("No duplicatesSet: " + noDuplicatesSet);
        Iterator<Integer> setIterator = noDuplicatesSet.iterator();
        while (setIterator.hasNext()) {
            Integer value = setIterator.next();
            duplicatesSortedArray[iterator] = value;
            iterator++;
        }
//        Arrays.stream(duplicatesSortedArray).forEach(intElement -> System.out.println(intElement));
    }

    private static boolean checkIfTheArrayIsSorted(int[] arr) {
        boolean isArraySorted = false;
        for (int index = 0; index < arr.length; index++) {
            if (index + 1 < arr.length) {
                if (arr[index] < arr[index + 1]) {
                    isArraySorted = true;
                } else {
                    isArraySorted = false;
                    break;
                }
            }
        }
        return isArraySorted;
    }

    private static int secondLargestElementOfArrayOptimalApproach(int[] arr) {
        int maxElement = arr[0];
        int secondMaxElement = -1;
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] > maxElement) {
                secondMaxElement = maxElement;
                maxElement = arr[index];
            }
        }
        return secondMaxElement;
    }

    private static int secondLargestElementOfArrayBruteForceAppraoch(int[] arr) {
        Arrays.sort(arr);
        int maxElement = arr[arr.length - 1];
        int secondMaxElement = 0;
        for (int index = arr.length - 2; index >= 0; index--) {
            if (arr[index] > secondMaxElement && arr[index] < maxElement) {
                secondMaxElement = arr[index];
            }
        }
        return secondMaxElement;
    }

    private static int largestElementOfArrayBruteForce(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }

    private static int secondLargestElementOfArrayBetterApproach(int[] arr) {
        int maxElement = arr[0];
        int secondMaxElement = arr[0];
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] > maxElement) {
                maxElement = arr[index];
            }
        }
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] > secondMaxElement && arr[index] < maxElement) {
                secondMaxElement = arr[index];
            }
        }
        return secondMaxElement;
    }

    private static int largestElementOfArrayBetterApproach(int[] arr) {
        int maxElement = arr[0];
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] > maxElement) {
                maxElement = arr[index];
            }
        }
        return maxElement;
    }
}
