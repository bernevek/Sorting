package com.LZSS;

import java.util.List;

import static com.LZSS.LZSSData.compress;
import static com.LZSS.LZSSData.decompress;

public class Main {

    public static void main(String[] args) throws Exception{
        String src = "ABABCDABCABCDCADABCA";
        LZSSData ret = compress(src);
        System.out.println(String.format("compress done. %d elements, %d chars", ret.getMatch().length(), ret.getDest().length()));

        StringBuilder b = decompress(ret);
        StringBuilder dest = ret.getDest();

        int sz = dest.length();
        int bsz = ret.getSize() / 8 + (((ret.getSize()) % 8 == 0) ? 0 : 1);
        boolean eq = src.equals(b.toString());
        System.out.println(String.format(
                "src: %d, comp: %d(%02.1f%%) + %dbytes, decomp: %d, %b",
                src.length(), sz, 1.0 *  sz / src.length() * 100, bsz, b.length(), eq));
        for(int i = 0; i < src.length(); i++){
            if(src.charAt(i) != b.charAt(i)){
                System.out.println(String.format(
                        "%dth char different [%c:%c]",
                        i, src.charAt(i), b.charAt(i)));
                int s = Math.max(i - 5, 0);
                int e = Math.min(i + 5, src.length());
                System.out.println("src: " + src.substring(s, e));
                System.out.println("dec: " + b.substring(s, e));
                break;
            }
        }
    }






    private static String toString(char c){
        if(c < 0x20){
            return String.format("(0x%02x)", (int)c);
        } else{
            return "" + c;
        }
    }

}
