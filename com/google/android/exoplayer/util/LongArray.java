// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import java.util.Arrays;

public final class LongArray
{
    private int size;
    private long[] values;
    
    public LongArray() {
        this(32);
    }
    
    public LongArray(final int n) {
        this.values = new long[n];
    }
    
    public void add(final long n) {
        if (this.size == this.values.length) {
            this.values = Arrays.copyOf(this.values, this.size * 2);
        }
        this.values[this.size++] = n;
    }
    
    public long get(final int n) {
        if (n < 0 || n >= this.size) {
            throw new IndexOutOfBoundsException("Invalid size " + n + ", size is " + this.size);
        }
        return this.values[n];
    }
    
    public int size() {
        return this.size;
    }
}
