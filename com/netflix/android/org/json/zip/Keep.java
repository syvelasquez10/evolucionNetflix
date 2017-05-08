// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

abstract class Keep implements None, PostMortem
{
    protected int capacity;
    protected int length;
    protected int power;
    protected long[] uses;
    
    public Keep(final int n) {
        this.capacity = JSONzip.twos[n];
        this.length = 0;
        this.power = 0;
        this.uses = new long[this.capacity];
    }
    
    public static long age(final long n) {
        if (n >= 32L) {
            return 16L;
        }
        return n / 2L;
    }
    
    public int bitsize() {
        while (JSONzip.twos[this.power] < this.length) {
            ++this.power;
        }
        return this.power;
    }
    
    public void tick(final int n) {
        final long[] uses = this.uses;
        ++uses[n];
    }
    
    public abstract Object value(final int p0);
}
