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
            algorithms.add(new ShellSort(Arrays.copyOf(initArray(array.length), array.length)));
            algorithms.add(new ShellSort(Arrays.copyOf(array, array.length)));
            algorithms.add(new QuickSortFirstElementPivot(Arrays.copyOf(array, array.length)));
            algorithms.add(new QuickSortRandomElementPivot(Arrays.copyOf(array, array.length)));
            algorithms.add(new QuickSortMedianOfThree(Arrays.copyOf(array, array.length)));
            algorithms.add(new QuickSortCombinedWithInsertion(Arrays.copyOf(array, array.length)));
        } else {
            algorithms.get(0).setArray(Arrays.copyOf(initArray(array.length), array.length));
            for (int i = 1; i < algorithms.size(); i++) {
                algorithms.get(i).setArray(Arrays.copyOf(array, array.length));
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
