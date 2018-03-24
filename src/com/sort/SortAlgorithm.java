package com.sort;

public abstract class SortAlgorithm {
    protected int[] array;

    public SortAlgorithm(int[] inputArray) {
        this.array = inputArray;
    }

    public abstract void sort();

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}
