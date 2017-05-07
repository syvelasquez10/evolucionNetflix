// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Comparator;

public class AlphanumComparator implements Comparator<String>
{
    private final String getChunk(final String s, final int n, int i) {
        final StringBuilder sb = new StringBuilder();
        final char char1 = s.charAt(i);
        sb.append(char1);
        int j;
        i = (j = i + 1);
        if (this.isDigit(char1)) {
            while (i < n) {
                final char char2 = s.charAt(i);
                if (!this.isDigit(char2)) {
                    break;
                }
                sb.append(char2);
                ++i;
            }
        }
        else {
            while (j < n) {
                final char char3 = s.charAt(j);
                if (this.isDigit(char3)) {
                    break;
                }
                sb.append(char3);
                ++j;
            }
        }
        return sb.toString();
    }
    
    private final boolean isDigit(final char c) {
        return c >= '0' && c <= '9';
    }
    
    @Override
    public int compare(final String s, final String s2) {
        if (s instanceof String && s2 instanceof String) {
            final int length = s.length();
            final int length2 = s2.length();
            int length3;
            int length4;
            for (int n = 0, n2 = 0; n2 < length && n < length2; n += length4, n2 += length3) {
                final String chunk = this.getChunk(s, length, n2);
                length3 = chunk.length();
                final String chunk2 = this.getChunk(s2, length2, n);
                length4 = chunk2.length();
                int compareTo;
                if (this.isDigit(chunk.charAt(0)) && this.isDigit(chunk2.charAt(0))) {
                    final int length5 = chunk.length();
                    final int n3 = length5 - chunk2.length();
                    if ((compareTo = n3) == 0) {
                        int i = 0;
                        compareTo = n3;
                        while (i < length5) {
                            compareTo = chunk.charAt(i) - chunk2.charAt(i);
                            final int n4;
                            if ((n4 = compareTo) != 0) {
                                return n4;
                            }
                            ++i;
                        }
                    }
                }
                else {
                    compareTo = chunk.compareTo(chunk2);
                }
                int n4;
                if ((n4 = compareTo) != 0) {
                    return n4;
                }
            }
            return length - length2;
        }
        return 0;
    }
}
