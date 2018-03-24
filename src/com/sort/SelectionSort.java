package com.sort;

public class SelectionSort extends SortAlgorithm {
    public SelectionSort(int[] inputArray) {
        super(inputArray);
    }

    @Override
    public void sort() {
        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                if (array[j] < array[index]) {
                    index = j;
                }
            }
            int smallerNumber = array[index];
            array[index] = array[i];
            array[i] = smallerNumber;
        }
    }
}
