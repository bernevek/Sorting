package com.LZSS;

import java.util.LinkedList;


public class Main {

    String text;
    StringBuilder textcpy;
    StringBuilder dictionary = new StringBuilder();
    String lookahead;
    LinkedList<String> subsets = new LinkedList<>();
    LinkedList<String> compressed = new LinkedList<>();



    public static void main(String[] args) throws Exception{
        Main main = new Main();
        main.setText("ABABCDABCABCDCADABCA");
        while (main.textcpy.length() >= 4) {
            main.compress();
        }
    }


    public void initDictionary() {
        for (int i = 0; i < 8; i++) {
            dictionary.append(text.charAt(0));
        }
    }

    public void setText(String text) {
        this.text = text;
        textcpy = new StringBuilder(text);
        initDictionary();
    }

    public void substrings(String str) {
        subsets.clear();
        if (str.length() > 1) {
            for (int i = 0; i <= str.length(); i++) {
                String s1 = str.substring(0, i); //get a substring of s from from the start to index i
                subsets.add(s1);
            }
        } else {
            subsets.add(str);
        }
    }


    void compress() {
        if (textcpy.length() >= 4) {
            lookahead = textcpy.substring(0, 4);
        } else {
            lookahead = textcpy.substring(0, textcpy.length());
        }
        substrings(lookahead);
        StringBuilder output = new StringBuilder("(");
        int maxlen = 0;
        int maxidx = -1;
        for (int i = 0; i < subsets.size(); i++) {
            int index = dictionary.lastIndexOf(subsets.get(i));
            if (dictionary.lastIndexOf(subsets.get(i)) != -1) { //if there is a match do this
                if (subsets.get(i).length() > maxlen) { //execute if substring is the longest so far
                    maxlen = subsets.get(i).length();
                    maxidx = index;
                }
            }
        }

        if (maxlen > 0) {//if match is found
            System.out.print("dict: " + dictionary + "\t");
            System.out.print("lookahead: " + lookahead + "\t");

            maxidx = 7 - maxidx;//reverse index since indexing in example is right to left
            output.append("0,");
            output.append(maxidx);
            output.append(",");
            output.append(maxlen);
            output.append(")");

            System.out.println(output.toString());
            compressed.add(output.toString());

            for (int j = 1; j <= maxlen; j++) {//shift the dictionary to the left by the length amount
                dictionary.deleteCharAt(0);
            }
            dictionary.delete(8 - maxlen, 8);
            dictionary.append(lookahead, 0, maxlen);
            textcpy.delete(0, maxlen);

        } else {
            System.out.print("dict: " + dictionary + "\t");
            System.out.print("lookahead: " + lookahead + "\t");
            output.append("1,");
            output.append(subsets.get(1));
            output.append(")");
            System.out.println(output.toString());
            compressed.add(output.toString());

            dictionary.deleteCharAt(0);
            dictionary.append(lookahead, 0, 1); //add the new char
            textcpy.delete(0, 1);
        }
    }
}
