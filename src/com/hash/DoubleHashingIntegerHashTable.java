package com.hash;

public class DoubleHashingIntegerHashTable extends LinearProbingIntegerHashTable {
    @Override
    protected int calculateHash(int x, int i) {
        int h1 = x % m;
        int h2 = ((2 * x) % (m - 1)) + 1;
        return (h1 + i * h2) % m;
    }
}
