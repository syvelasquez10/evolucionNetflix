// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

public class MathUtils
{
    public static int ceiling(final int n, final int n2) {
        return (n + n2 - 1) / n2;
    }
    
    public static int constrain(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    public static int divideInts(final int n, final int n2) {
        return (int)(n / n2 + 0.5f);
    }
    
    public static class Range
    {
        private final int end;
        private final int midpoint;
        private final int start;
        
        public Range(final int start, final int end) {
            this.start = start;
            this.end = end;
            this.midpoint = MathUtils.divideInts(start + end, 2);
        }
        
        public boolean contains(final int n) {
            return n >= this.start && n <= this.end;
        }
        
        public int getMidpoint() {
            return this.midpoint;
        }
        
        @Override
        public String toString() {
            return "Range [start=" + this.start + ", end=" + this.end + "]";
        }
    }
}
