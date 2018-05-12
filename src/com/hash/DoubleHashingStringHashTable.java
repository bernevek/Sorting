package com.hash;

public class DoubleHashingStringHashTable extends LinearProbingStringHashTable {
    protected int calculateHash(String s, int a) {
        int h = 0;
        int c1 = 31415;
        int c2 = 27183;

        for (int i = 0; i < s.length(); i++) {
            h = (c1 * h + s.charAt(i)) % a;
            c1 = (c1 * c2) % (a - 1);
        }
        if (h < 0) {
            return h + a;
        } else {
            return h;
        }
    }
}
