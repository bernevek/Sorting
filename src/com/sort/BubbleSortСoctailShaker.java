package com.sort;

public class BubbleSortСoctailShaker extends SortAlgorithm {
    public BubbleSortСoctailShaker(int[] inputArray) {
        super(inputArray);
    }

    @Override
    public void sort() {
        int size = array.length;
        int lastSwap = 0;
        int temp = 0;
        for (int i = 0; i < size / 2; i++) {
            int currentSwap = 0;
            for (int j = lastSwap + 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    currentSwap = j;
                }
            }
            lastSwap = currentSwap;

            for (int j = lastSwap - 1; j >= i + 1; j--) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    currentSwap = j;
                }
            }
            lastSwap = currentSwap;
        }
    }
}
