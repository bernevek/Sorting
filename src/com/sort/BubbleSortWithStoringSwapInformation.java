package com.sort;

public class BubbleSortWithStoringSwapInformation extends SortAlgorithm {
    public BubbleSortWithStoringSwapInformation(int[] inputArray) {
        super(inputArray);
    }

    @Override
    public void sort() {
        boolean swapped = true;
        int size = array.length;
        int temp = 0;
        for (int i = 0; swapped && i < size; i++) {
            swapped = false;
            for (int j = 1; j < (size - i); j++) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    swapped = true;
                }
            }
        }
    }
}
