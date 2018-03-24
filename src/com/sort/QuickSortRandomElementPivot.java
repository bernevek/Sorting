package com.sort;

import static com.sort.Main.random;

public class QuickSortRandomElementPivot extends SortAlgorithm {
    public QuickSortRandomElementPivot(int[] inputArray) {
        super(inputArray);
    }

    @Override
    public void sort() {
        sort(array, 0, array.length - 1);
    }

    private void sort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            sort(arr, left, index - 1);
        if (index < right)
            sort(arr, index, right);
    }

    private int partition(int arr[], int left, int right) {
        int i = left, j = right;
        int pivot = arr[left + random.nextInt(right - left)];
        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    private void swap(int[] array, int i, int j) {
        if (i >= 0 && j >= 0 && i < array.length && j < array.length) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }
}
