// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

public class MathUtils$Range
{
    private final int end;
    private final int midpoint;
    private final int start;
    
    public MathUtils$Range(final int start, final int end) {
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
