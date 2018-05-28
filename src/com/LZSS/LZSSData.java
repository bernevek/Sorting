package com.LZSS;

import java.io.IOException;
import java.util.*;

public class LZSSData {

    private BitSet match = new BitSet();
    private StringBuilder dest = new StringBuilder();
    private int size;

    public LZSSData(BitSet match, StringBuilder dest, int size) {
        this.match = match;
        this.dest = dest;
        this.size = size;
    }

    public BitSet getMatch() {
        return match;
    }

    public void setMatch(BitSet match) {
        this.match = match;
    }

    public StringBuilder getDest() {
        return dest;
    }

    public void setDest(StringBuilder dest) {
        this.dest = dest;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static LZSSData compress(CharSequence src)
            throws IOException {
        BitSet match = new BitSet();
        StringBuilder out = new StringBuilder();
        int size = 0;
        Map<Character, List<Integer>> startPoss = new HashMap<Character, List<Integer>>();
        int n = src.length();
        for(int i = 0; i < n; i++){
            char target = src.charAt(i);
            // find longest match
            boolean found = false;
            int start = 0;
            int matchLen = 0;
            List<Integer> poss = startPoss.get(target);
            if(poss != null){
                Iterator<Integer> it = poss.iterator();
                while(it.hasNext()){
                    int s = it.next();
                    if((i - s) > 3){
                        it.remove();
                        continue;
                    }
                    int len = getMatchedLen(src, s + 1, i + 1, n) + 1;
                    if(len > matchLen){
                        start = i - s;
                        matchLen = len;
                    }
                    found = true;
                }
                poss.add(i);
                int jn = Math.min(i + matchLen, n);
                for(int j = i + 1; j < jn; j++){
                    List<Integer> p = startPoss.get(src.charAt(j));
                    if(p == null){
                        p = new LinkedList<Integer>();
                        startPoss.put(src.charAt(j), p);
                    }
                    p.add(j);
                }
            } else{
                poss = new LinkedList<Integer>();
                poss.add(i);
                startPoss.put(target, poss);
            }
            if(found && matchLen > 1){
                match.set(size);
                out.append((char)start)
                        .append((char)matchLen);
                i = i + matchLen - 1;
            } else{
                match.set(size, false);
                out.append(target);
            }
            size++;
        }
        return new LZSSData(match, out, size);
    }

    public static StringBuilder decompress(LZSSData src){
        StringBuilder out = new StringBuilder();
        int index = 0;
        int n = src.size;
        for(int i = 0; i < n; i++){
            if(src.match.get(i)){
                int start = src.dest.charAt(index++);
                int matchedLen = src.dest.charAt(index++);
                int s = out.length() - start;
                int e = s + matchedLen;
                for(; s < e; s++){
                    out.append(out.charAt(s));
                }
            } else{
                out.append(src.dest.charAt(index++));
            }
        }
        return out;
    }


    private static int getMatchedLen(CharSequence src, int i1, int i2, int end){
        int n = Math.min(i2 - i1, end - i2);
        for(int i = 0; i < n; i++){
            if(src.charAt(i1++) != src.charAt(i2++)) return i;
        }
        return 0;
    }
}
