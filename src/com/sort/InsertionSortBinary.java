package com.sort;

import java.util.Arrays;

public class InsertionSortBinary extends SortAlgorithm {
    public InsertionSortBinary(int[] inputArray) {
        super(inputArray);
    }

    @Override
    public void sort() {
        int size = array.length;
        for (int i = 1; i < size; i++) {
            int x = array[i];
            // Find location to insert using binary search
            int j = Math.abs(Arrays.binarySearch(array, 0, i, x) + 1);
            //Shifting array to one location right
            System.arraycopy(array, j, array, j + 1, i - j);
            //Placing element at its correct location
            array[j] = x;
        }
    }
}
