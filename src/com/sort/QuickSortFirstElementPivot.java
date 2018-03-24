package com.sort;

public class QuickSortFirstElementPivot extends SortAlgorithm {
    public QuickSortFirstElementPivot(int[] inputArray) {
        super(inputArray);
    }

    @Override
    public void sort() {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int first, int last) {
        if (first < last) {
            // select pivot element (left-most)
            int pivot = array[first];
            // partition and shuffle around pivot
            int i = first;
            int j = last;
            while (i < j) {
                // move right to avoid pivot element
                i += 1;
                // scan right: find elements greater than pivot
                while (i <= last && array[i] < pivot) {
                    i += 1;
                }
                // scan left: find elements smaller than pivot
                while (j >= first && array[j] > pivot) {
                    j -= 1;
                }
                if (i <= last && i < j) {
                    // swap around pivot
                    swap(array, i, j);
                }
            }
            // put pivot in correct place
            swap(array, first, j);
            // sort partitions
            sort(array, first, j - 1);
            sort(array, j + 1, last);
        }
    }

    private void swap(int[] array, int i, int j) {
        if (i >= 0 && j >= 0 && i < array.length && j < array.length) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }
}
