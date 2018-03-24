package com.sort;

public class BubbleSortWithStoringPositionLastChange extends SortAlgorithm {
    public BubbleSortWithStoringPositionLastChange(int[] inputArray) {
        super(inputArray);
    }

    @Override
    public void sort() {
        int size = array.length;
        int lastSwap = size - 1;
        int temp = 0;
        for (int i = 0; i < size; i++) {
            int currentSwap = -1;
            for (int j = 1; j <= lastSwap; j++) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    currentSwap = j;
                }
            }
            lastSwap = currentSwap;
            if (lastSwap == -1) {
                break;
            }
        }
    }
}
