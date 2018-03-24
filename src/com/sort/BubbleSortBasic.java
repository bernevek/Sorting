package com.sort;

public class BubbleSortBasic extends SortAlgorithm {
    public BubbleSortBasic(int[] inputArray) {
        super(inputArray);
    }

    @Override
    public void sort() {
        int size = array.length;
        int temp = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < (size - i); j++) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
