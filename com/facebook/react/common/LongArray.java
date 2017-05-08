// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.common;

public class LongArray
{
    private long[] mArray;
    private int mLength;
    
    private LongArray(final int n) {
        this.mArray = new long[n];
        this.mLength = 0;
    }
    
    public static LongArray createWithInitialCapacity(final int n) {
        return new LongArray(n);
    }
    
    private void growArrayIfNeeded() {
        if (this.mLength == this.mArray.length) {
            final long[] mArray = new long[Math.max(this.mLength + 1, (int)(this.mLength * 1.8))];
            System.arraycopy(this.mArray, 0, mArray, 0, this.mLength);
            this.mArray = mArray;
        }
    }
    
    public void add(final long n) {
        this.growArrayIfNeeded();
        this.mArray[this.mLength++] = n;
    }
    
    public void dropTail(final int n) {
        if (n > this.mLength) {
            throw new IndexOutOfBoundsException("Trying to drop " + n + " items from array of length " + this.mLength);
        }
        this.mLength -= n;
    }
    
    public long get(final int n) {
        if (n >= this.mLength) {
            throw new IndexOutOfBoundsException("" + n + " >= " + this.mLength);
        }
        return this.mArray[n];
    }
    
    public void set(final int n, final long n2) {
        if (n >= this.mLength) {
            throw new IndexOutOfBoundsException("" + n + " >= " + this.mLength);
        }
        this.mArray[n] = n2;
    }
    
    public int size() {
        return this.mLength;
    }
}
