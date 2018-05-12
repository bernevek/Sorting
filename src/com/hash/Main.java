package com.hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    int[] load = {50, 70, 80, 90};
    private Random random = new Random();
    private LinearProbingIntegerHashTable linearProbingIntegerHashTable = new LinearProbingIntegerHashTable();
    private LinearProbingStringHashTable linearProbingStringHashTable = new LinearProbingStringHashTable();
    private DoubleHashingIntegerHashTable doubleHashingIntegerHashTable = new DoubleHashingIntegerHashTable();
    private DoubleHashingStringHashTable doubleHashingStringHashTable = new DoubleHashingStringHashTable();
    private List<String> words = new ArrayList<>();
    private List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.read();
        main.searchHitAndMissInteger();
        System.out.println("***************************************************");
        main.searchHitAndMissString();
    }

    public void searchHitAndMissInteger() {
        for (int i : load) {
            initIntegerHashTables(i);
            if (linearProbingIntegerHashTable.search(numbers.get(400000)))
                System.out.print("    Load " + i + "% integer linear hit = " + linearProbingIntegerHashTable.getCount());
            if (!linearProbingIntegerHashTable.search(numbers.get(900000)))
                System.out.println("    Load " + i + "% integer linear miss = " + linearProbingIntegerHashTable.getCount());
            if (doubleHashingIntegerHashTable.search(numbers.get(400000)))
                System.out.print("    Load " + i + "% integer double hit = " + doubleHashingIntegerHashTable.getCount());
            if (!doubleHashingIntegerHashTable.search(numbers.get(900000)))
                System.out.println("    Load " + i + "% integer double miss = " + doubleHashingIntegerHashTable.getCount());
        }
    }

    public void searchHitAndMissString() {
        for (int i : load) {
            initStringHashTables(i);
            if (linearProbingStringHashTable.search(words.get(400000 / 1000)))
                System.out.print("    Load " + i + "% string linear hit = " + linearProbingStringHashTable.getCount());
            if (!linearProbingStringHashTable.search(words.get(900000)))
                System.out.println("    Load " + i + "% string linear miss = " + linearProbingStringHashTable.getCount());
            if (doubleHashingStringHashTable.search(words.get(400000 / 1000)))
                System.out.print("    Load " + i + "% string double hit = " + doubleHashingStringHashTable.getCount());
            if (!doubleHashingStringHashTable.search(words.get(900000)))
                System.out.println("    Load " + i + "% string double miss = " + doubleHashingStringHashTable.getCount());
        }
    }

    public void initStringHashTables(int load) {
        linearProbingStringHashTable.clear();
        doubleHashingStringHashTable.clear();
        for (int i = 0; i < words.size() * load / 10000; i++) {
            linearProbingStringHashTable.insert(words.get(i));
            doubleHashingStringHashTable.insert(words.get(i));
        }
    }

    public void initIntegerHashTables(int load) {
        linearProbingIntegerHashTable.clear();
        doubleHashingIntegerHashTable.clear();
        for (int i = 0; i < numbers.size() * load / 100; i++) {
            linearProbingIntegerHashTable.insert(numbers.get(i));
            doubleHashingIntegerHashTable.insert(numbers.get(i));
        }
    }

    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\words.txt"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\numbers.txt"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.valueOf(line));
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
