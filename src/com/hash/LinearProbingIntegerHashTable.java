package com.hash;

public class LinearProbingIntegerHashTable {
    protected int[] table = new int[1000000];
    protected int m = table.length;
    protected int count = 0;

    protected int calculateHash(int x, int i) {
        int h1 = x % m;
        return (h1 + 3 * i) % m;
    }

    public boolean insert(int x) {
        int hash;
        for (int i = 0; i < m; i++) {
            hash = calculateHash(x, i);
            if (table[hash] == 0 || table[hash] == x) {
                table[hash] = x;
                return true;
            }
        }
        return false;
    }

    public boolean search(int x) {
        int hash;
        count = 0;
        for (int i = 0; i < m; i++) {
            count++;
            hash = calculateHash(x, i);
            if (table[hash] == x) {
                return true;
            }
            if (table[hash] == 0) {
                return false;
            }
        }
        return false;
    }

    public boolean remove(int x) {
        int hash;
        for (int i = 0; i < m; i++) {
            hash = calculateHash(x, i);
            if (table[hash] == x) {
                table[hash] = 0;
                return true;
            }
            if (table[hash] == 0) {
                return false;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = 0;
        }
    }

    public int[] getTable() {
        return table;
    }

    public int getCount() {
        int k = count;
        count = 0;
        return k;
    }
}
