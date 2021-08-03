// 2021-06-07
// by Sergio Marchio
//
// Quick sort implementation
// Algorithm description from Wikipedia: https://en.wikipedia.org/wiki/Quicksort
// Important points:
//    - recursive sorting
//    - in-place sorting

package com.solvd.quickSort.runner;

public class Runner {
    static void quickSort(int[] numberList) {
        quickSort(numberList, 0, numberList.length - 1);
    }

    static void quickSort(int[] numberList, int start, int end) {

        if (end <= start) {
            return;
        }

        // compare all items with last element (pivot)
        int p = end;

        // When i == p is the last element
        // if element > pivot, p is updated instead of i!
        // if 2 elements p-1 = i :)
        int i = start;
        while (i < p) {
            if (numberList[i] <= numberList[p]) {
                i++;
            } else {
                int pivot = numberList[p];
                numberList[p] = numberList[i];
                numberList[i] = numberList[p - 1];
                numberList[p - 1] = pivot;

                // Now pivot position is changed!
                p--;
            }
        }

        // lower half
        quickSort(numberList, start, p - 1);
        // upper half
        quickSort(numberList, p, end);
    }

    public final static void main(String[] args) {
        int[] numberList = new int[args.length];

        System.out.println("Array to order:");

        // check data type:
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");
            numberList[i] = Integer.parseInt(args[i]);
        }

        System.out.println();

        quickSort(numberList);

        System.out.println("Ordered array:");

        for (int i = 0; i < numberList.length; i++) {
            System.out.print(numberList[i] + " ");
        }


    }
}
