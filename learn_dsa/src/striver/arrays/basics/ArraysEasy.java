package striver.arrays.basics;

import java.util.*;

public class ArraysEasy {
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 4};
        int[] sarr = {4, 5, 1, 2, 3};
        int[] newArr = {1, 2, 3, 3, 4, 4};
        int[] sortedDuplicates = {1, 2, 2, 3, 3, 4, 5};
        int[] newSortedDups = {1, 2, 2, 3, 3, 4, 5};
        int[] leftArr = {1, 2, 3, 4, 5};
        int[] rightArr = {1, 2, 3, 4, 5};
        int[] leftArrNew = {1, 2, 3, 4, 5};
        int[] newLeftArr = {1, 2, 3, 4, 5, 6, 7};
        int[] arrayWithZeros = {0, 1, 0, 2, 3, 0, 2, 0, 4, 0, 0, 5, 1};
        int[] arrayWithZerosNew = {0, 1, 2, 3, 0, 4, 5, 7, 0};
        int[] arrayElementsLs = {1, 2, 3, 4, 5, 5, 6, 7};
        int[] arrFirst = {1, 1, 2, 3, 4, 5};
        int[] arrSecond = {2, 3, 4, 4, 5};
        System.out.println("Largest Element Optimal: " + largestElementInArray(arr));
        System.out.println("Largest Element Brute force: " + largestElementBrute(arr));
        System.out.println("Second Largest Brute: " + secondLargestBrute(arr));
        System.out.println("Second Largest Better: " + secondLargestBetter(arr));
        System.out.println("Second Largest Optimal: " + secondLargestOptimal(arr));
        System.out.println("Second smallest optimal: " + secondSmallest(sarr));
        System.out.println("Is Array sorted: " + checkIfArrayIsSorted(newArr));
        System.out.println("Total non duplicates elements: " + removeDuplicatesInPlaceFromSortedArrayBrute(sortedDuplicates));
        System.out.println("Optimal way to identify and refill unique elements in array: " + removeDuplicatesFromSortedArrayOptimal(newSortedDups));
        System.out.println("Left Rotate by one place: " + Arrays.toString(leftRotateByOnePlace(leftArr)));
        System.out.println("Right rotate by one place: " + Arrays.toString(rightRotateByOnePlace(rightArr)));
        System.out.println("Left Rotate array by k places brute force: " + Arrays.toString(leftRotateByKPlacesBruteForce(leftArrNew, 6)));
        System.out.println("Left Rotate array by k places optimal: " + Arrays.toString(leftRotateArrayByKPlacesOptimal(newLeftArr, 3)));
        System.out.println("After: Move Zeros to the end brute force: " + Arrays.toString(moveZerosToTheEndBruteForce(arrayWithZeros)));
        System.out.println("After Optimal: Move Zeros to the end optimal approach: " + Arrays.toString(moveZerosToTheEndOptimal(arrayWithZerosNew)));
        System.out.println("Element first occurrence linear search: " + elementFirstOccurrenceLinearSearch(arrayElementsLs, 5));
        System.out.println("Brute force union: " + Arrays.toString(unionOfSortedArraysBruteForce(arrFirst, arrSecond)));
        System.out.println("Brute force intersection: " + Arrays.toString(intersectionOfSortedArraysBruteForce(arrFirst, arrSecond)));
    }

    public static int largestElementInArray(int[] arr) {
        int maxElement = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxElement) {
                maxElement = arr[i];
            }
        }
        return maxElement;
    }

    public static int largestElementBrute(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }

    public static int secondLargestBrute(int[] arr) {
        Arrays.sort(arr);
        int largest = arr[arr.length - 1];
        int secondLargest = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }

        }
        return secondLargest;
    }

    public static int secondLargestBetter(int[] arr) {
        int largest = arr[0];
        int secondLargest = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    public static int secondLargestOptimal(int[] arr) {
        int largest = arr[0];
        int secondLargest = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest && arr[i] > secondLargest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    //[4,5,1,2,3]
    public static int secondSmallest(int[] arr) {
        int smallest = arr[0];
        int secondSmallest = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest && arr[i] < secondSmallest) {
                secondSmallest = smallest;
                smallest = arr[i];
            } else if (arr[i] < secondSmallest) {
                secondSmallest = arr[i];
            }
        }
        return secondSmallest;
    }

    //1,2,5,3,4
    public static boolean checkIfArrayIsSorted(int[] arr) {
        boolean isArraySorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                isArraySorted = false;
                break;
            }
        }
        return isArraySorted;
    }


    public static int removeDuplicatesInPlaceFromSortedArrayBrute(int[] arr) {
        Set<Integer> elements = new LinkedHashSet<>();
        for (Integer element : arr) {
            elements.add(element);
        }
        int iterator = 0;
        for (Integer element : elements) {
            arr[iterator] = element;
            iterator++;
        }
        for (int i = iterator; i < arr.length; i++) {
            arr[i] = 0;
        }
        System.out.println("Remove duplicates in place from sorted array: " + Arrays.toString(arr));
        return elements.size();
    }

    public static int removeDuplicatesFromSortedArrayOptimal(int[] arr) {
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if (arr[i] != arr[j]) {
                arr[i + 1] = arr[j];
                i++;
            }
        }
        System.out.println("Removed duplicates using optimal approach: " + Arrays.toString(arr));
        return i + 1;
    }

    //1,2,3,4,5 => 2,3,4,5,1
    public static int[] leftRotateByOnePlace(int[] arr) {
        System.out.println("Before left rotation: " + Arrays.toString(arr));
        int firstElement = arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        arr[arr.length - 1] = firstElement;
        return arr;
    }

    //1,2,3,4,5 => 5,1,2,3,4
    public static int[] rightRotateByOnePlace(int[] arr) {
        System.out.println("Before right rotation by one place: " + Arrays.toString(arr));
        int lastElement = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = lastElement;
        return arr;
    }

    //1,2,3,4,5 => 3,4,5,1,2
    public static int[] leftRotateByKPlacesBruteForce(int[] arr, int places) {
        System.out.println("Before left rotation by k places brute force: " + Arrays.toString(arr));
        System.out.println("Places: " + places);
        places = places % arr.length;
        Set<Integer> elements = new LinkedHashSet<>();
        int setIterator = 0;
        int arrayIterator = 0;
        for (int i = 0; i < places; i++) {
            elements.add(arr[i]);
            setIterator++;
        }
        for (int i = setIterator; i < arr.length; i++) {
            arr[i - places] = arr[i];
            arrayIterator++;
        }
        for (Integer element : elements) {
            arr[arrayIterator] = element;
            arrayIterator++;
        }
        return arr;
    }

    //1,2,3,4,5,6,7
    //Left Rotation by 3 places: 4,5,6,7,1,2,3
    //3,2,1 || 7,6,5,4
    //4,5,6,7,1,2,3
    public static int[] leftRotateArrayByKPlacesOptimal(int[] arr, int places) {
        System.out.println("Before left rotation by k places optimal: " + Arrays.toString(arr));
        places = places % arr.length;
        reverseArray(arr, 0, places - 1);
        reverseArray(arr, places, arr.length - 1);
        reverseArray(arr, 0, arr.length - 1);
        return arr;
    }

    public static int[] reverseArray(int[] arr, int startIndex, int endIndex) {
        while (startIndex <= endIndex) {
            int temp = arr[startIndex];
            arr[startIndex] = arr[endIndex];
            arr[endIndex] = temp;
            startIndex++;
            endIndex--;
        }
        return arr;
    }

    public static int[] moveZerosToTheEndBruteForce(int[] arr) {
        System.out.println("Before moving zeros to the end brute force: " + Arrays.toString(arr));
        int tempArr[] = new int[arr.length];
        int iterator = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                tempArr[iterator] = arr[i];
                iterator++;
            }
        }
        for (int i = 0; i < tempArr.length; i++) {
            arr[i] = tempArr[i];
        }
        for (int i = iterator; i < arr.length; i++) {
            arr[i] = 0;
        }
        return arr;
    }

    public static int[] moveZerosToTheEndOptimal(int[] arr) {
        System.out.println("Before condition of the array: " + Arrays.toString(arr));
        int j = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                j = i;
                break;
            }
        }
        for (int i = j + 1; i < arr.length; i++) {
            if (arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
        return arr;
    }

    public static int elementFirstOccurrenceLinearSearch(int[] arr, int element) {
        int elementIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                elementIndex = i;
                break;
            }
        }
        return elementIndex;
    }

    public static int[] unionOfSortedArraysBruteForce(int[] arrayFirst, int[] arraySecond) {
        System.out.println("Before union of sorted array: ");
        System.out.println("First Array: " + Arrays.toString(arrayFirst));
        System.out.println("Second Array: " + Arrays.toString(arraySecond));
        Set<Integer> elements = new LinkedHashSet<>();
        for (int i = 0; i < arrayFirst.length; i++) {
            elements.add(arrayFirst[i]);
        }
        for (int i = 0; i < arraySecond.length; i++) {
            elements.add(arraySecond[i]);
        }
        int iterator = 0;
        int[] unionArray = new int[elements.size()];
        for (Integer element : elements) {
            unionArray[iterator] = element;
            iterator++;
        }
        return unionArray;
    }

    public static int[] intersectionOfSortedArraysBruteForce(int[] arrayFirst, int[] arraySecond) {
        System.out.println("Logging array elements before intersection: ");
        System.out.println("First array elements: " + Arrays.toString(arrayFirst));
        System.out.println("Second array elements: " + Arrays.toString(arraySecond));
        Set<Integer> elements = new LinkedHashSet<>();
        Set<Integer> intersection = new LinkedHashSet<>();
        for (int i = 0; i < arrayFirst.length; i++) {
            elements.add(arrayFirst[i]);
        }
        for (int i = 0; i < arraySecond.length; i++) {
            if (elements.contains(arraySecond[i])) {
                intersection.add(arraySecond[i]);
            }
        }
        int iterator = 0;
        int[] tempArray = new int[intersection.size()];
        for (Integer element : intersection) {
            tempArray[iterator] = element;
            iterator++;
        }
        return tempArray;
    }

    /*public static int[] unionOfSortedArrayOptimal(int[] arrayFirst, int[] arraySecond){
        System.out.println("Arrays before union by using optimal approach: ");
        System.out.println("First array: "+Arrays.toString(arrayFirst));
        System.out.println("Second array: "+Arrays.toString(arraySecond));
        int n1=arrayFirst.length;
        int n2=arraySecond.length;
        int i=0,j=0;
        while(i<n1 && j<n2){
            

        }
    }*/
}
