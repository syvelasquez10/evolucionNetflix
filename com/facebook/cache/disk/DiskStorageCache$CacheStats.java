// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

class DiskStorageCache$CacheStats
{
    private long mCount;
    private boolean mInitialized;
    private long mSize;
    
    DiskStorageCache$CacheStats() {
        this.mInitialized = false;
        this.mSize = -1L;
        this.mCount = -1L;
    }
    
    public long getSize() {
        synchronized (this) {
            return this.mSize;
        }
    }
    
    public void increment(final long n, final long n2) {
        synchronized (this) {
            if (this.mInitialized) {
                this.mSize += n;
                this.mCount += n2;
            }
        }
    }
    
    public boolean isInitialized() {
        synchronized (this) {
            return this.mInitialized;
        }
    }
    
    public void reset() {
        synchronized (this) {
            this.mInitialized = false;
            this.mCount = -1L;
            this.mSize = -1L;
        }
    }
    
    public void set(final long mSize, final long mCount) {
        synchronized (this) {
            this.mCount = mCount;
            this.mSize = mSize;
            this.mInitialized = true;
        }
    }
}
