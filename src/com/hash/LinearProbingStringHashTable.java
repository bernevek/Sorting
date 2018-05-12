package com.hash;

public class LinearProbingStringHashTable {
    protected String[] table = new String[1000000];
    protected int m = table.length;
    protected int count = 0;


    protected int calculateHash(String s, int a) {
        int h = 0;
        int c = 29;
        for (int i = 0; i < s.length(); i++) {
            h = (c * h + s.charAt(i)) % a;
        }
        return h;
    }

    public boolean insert(String s) {
        int hash;
        for (int i = 0; i < m; i++) {
            hash = calculateHash(s, m);
            if (table[hash] == null || table[hash].equals(s)) {
                table[hash] = s;
                return true;
            }
        }
        return false;
    }

    public boolean search(String s) {
        int hash;
        count = 0;
        for (int i = 0; i < m; i++) {
            count++;
            hash = calculateHash(s, m);
            if (table[hash] == null) {
                return false;
            }
            if (table[hash].equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(String s) {
        int hash;
        for (int i = 0; i < m; i++) {
            hash = calculateHash(s, m);
            if (table[hash] == null) {
                return false;
            }
            if (table[hash].equals(s)) {
                table[hash] = null;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    public String[] getTable() {
        return table;
    }

    public int getCount() {
        int k = count;
        count = 0;
        return k;
    }
}
