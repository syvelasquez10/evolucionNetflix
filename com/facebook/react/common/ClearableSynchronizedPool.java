// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.common;

import android.support.v4.util.Pools$Pool;

public class ClearableSynchronizedPool<T> implements Pools$Pool<T>
{
    private final Object[] mPool;
    private int mSize;
    
    public ClearableSynchronizedPool(final int n) {
        this.mSize = 0;
        this.mPool = new Object[n];
    }
    
    @Override
    public T acquire() {
        Object o = null;
        synchronized (this) {
            if (this.mSize != 0) {
                --this.mSize;
                final int mSize = this.mSize;
                o = this.mPool[mSize];
                this.mPool[mSize] = null;
            }
            return (T)o;
        }
    }
    
    public void clear() {
        int i = 0;
        synchronized (this) {
            while (i < this.mSize) {
                this.mPool[i] = null;
                ++i;
            }
            this.mSize = 0;
        }
    }
    
    @Override
    public boolean release(final T t) {
        synchronized (this) {
            boolean b;
            if (this.mSize == this.mPool.length) {
                b = false;
            }
            else {
                this.mPool[this.mSize] = t;
                ++this.mSize;
                b = true;
            }
            return b;
        }
    }
}
