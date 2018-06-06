package com.dynamic;

import java.util.LinkedList;

public class Main {


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        System.out.println("LCS");
        String a = "XMJYAUZ";
        String b = "MZJAWXU";
        System.out.println(a);
        System.out.println(b);
        int L[][] = main.lcsLength(b.toCharArray(), a.toCharArray());
        main.lcsPrint(L, b.toCharArray(), a.toCharArray());
        System.out.println("\nedit distance");
        a = "INTENTION";
        b = "EXECUTION";
        System.out.println(a);
        System.out.println(b);
        L = main.editDistance(a.toCharArray(), b.toCharArray());
        main.editDistancePrint(L, a.toCharArray(), b.toCharArray());
    }

    public int[][] lcsLength(char[] a, char[] b) {
        int m = a.length;
        int n = b.length;
        int L[][] = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (a[i - 1] == b[j - 1])
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }
        System.out.println(L[m][n]);
        return L;
    }

    public void lcsPrint(int[][] L, char[] a, char[] b) {
        int m = a.length;
        int n = b.length;
        int index = L[m][n];
        char[] lcs = new char[index];
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                lcs[index - 1] = a[i - 1];
                i--;
                j--;
                index--;
            } else if (L[i - 1][j] > L[i][j - 1])
                i--;
            else
                j--;
        }
        for (char c : lcs) {
            System.out.print(c);
        }
        System.out.println();
    }

    public int[][] editDistance(char[] str1, char[] str2) {
        int m = str1.length;
        int n = str2.length;
        int dp[][] = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (str1[i - 1] == str2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + min(dp[i][j - 1],
                            dp[i - 1][j],
                            dp[i - 1][j - 1]);
            }
        }
        System.out.println(dp[m][n]);
        return dp;
    }

    public void editDistancePrint(int[][] L, char[] a, char[] b) {
        int m = a.length;
        int n = b.length;
        LinkedList<String> result = new LinkedList<>();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                result.addFirst("" + a[i - 1]);
                i--;
                j--;
            } else if (L[i][j] - 1 == L[i][j - 1]) {
                result.addFirst("ins(" + b[j - 1] + ")");
                j--;
            } else if (L[i][j] - 1 == L[i - 1][j]) {
                result.addFirst("del(" + a[i - 1] + ")");
                i--;
            } else {
                result.addFirst("sub(" + a[i - 1] + ", " + b[j - 1] + ")");
                i--;
                j--;
            }
        }
        for (String s : result) {
            System.out.print(s + "  ");
        }
        System.out.println();
    }

    private int min(int x, int y, int z) {
        if (x <= y && x <= z) return x;
        if (y <= x && y <= z) return y;
        else return z;
    }


}
