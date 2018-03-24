package com.sort;

public class QuickSortMedianOfThree extends SortAlgorithm {
    public QuickSortMedianOfThree(int[] inputArray) {
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

        int pivot = medianOf3(array, left, right);

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

    private int medianOf3(int[] intArray, int left, int right) {
        int center = (left + right) / 2;

        if (intArray[left] > intArray[center])
            swap(intArray, left, center);

        if (intArray[left] > intArray[right])
            swap(intArray, left, right);

        if (intArray[center] > intArray[right])
            swap(intArray, center, right);

        swap(intArray, center, right - 1);
        return intArray[right - 1];
    }

    private void swap(int[] array, int i, int j) {
        if (i >= 0 && j >= 0 && i < array.length && j < array.length) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }
}
