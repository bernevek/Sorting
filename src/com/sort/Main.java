package com.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    static Random random = new Random();

    public static void main(String[] args) {
        int[] array = null;
        List<SortAlgorithm> algorithms = new ArrayList<>();
        int[] numbersOfElements = {8000, 16000, 32000, 64000, 128000};
//        int[] numbersOfElements = {8000};

        for (int numbersOfElement : numbersOfElements) {
            array = initArray(numbersOfElement);
            initAlgorithms(algorithms, array);
            long durability;
            System.out.println("Number of elements = " + numbersOfElement);
            for (SortAlgorithm algorithm : algorithms) {
                durability = System.currentTimeMillis();
                algorithm.sort();
                durability = System.currentTimeMillis() - durability;
                System.out.println("    algorithm - " + algorithm.getClass().getSimpleName() +
                        ", durability = " + durability + " ms, sorted = " + checkIfSorted(algorithm.getArray()));
            }
        }
    }

    public static void initAlgorithms(List<SortAlgorithm> algorithms, int[] array) {
        if (algorithms.isEmpty()) {
            algorithms.add(new SelectionSort(Arrays.copyOf(array, array.length)));
            algorithms.add(new InsertionSortBasic(Arrays.copyOf(array, array.length)));
            algorithms.add(new InsertionSortBinary(Arrays.copyOf(array, array.length)));
            algorithms.add(new BubbleSortBasic(Arrays.copyOf(array, array.length)));
            algorithms.add(new BubbleSortWithStoringSwapInformation(Arrays.copyOf(array, array.length)));
            algorithms.add(new BubbleSortWithStoringPositionLastChange(Arrays.copyOf(array, array.length)));
            algorithms.add(new BubbleSortСoctailShaker(Arrays.copyOf(array, array.length)));
        } else {
            for (SortAlgorithm algorithm : algorithms) {
                algorithm.setArray(array);
            }
        }
    }

    public static int[] initArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(200000);
        }
        return array;
    }

    public static int checkIfSorted(int[] arr) {
        int i = 1;
        int isSorted = 1;
        while ((i < arr.length) && isSorted == 1) {
            if (arr[i - 1] > arr[i]) {
                isSorted = 0;
                break;
            }
            i++;
        }
        return (isSorted);
    }

}
