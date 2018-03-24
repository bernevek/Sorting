package com.sort;

public class InsertionSortBasic extends SortAlgorithm {
    public InsertionSortBasic(int[] inputArray) {
        super(inputArray);
    }

    @Override
    public void sort() {
        int size = array.length;
        int temp;
        for (int i = 1; i < size; ++i) {
            temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }
}
