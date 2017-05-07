// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import android.os.Bundle;
import com.google.android.gms.common.api.Releasable;

public abstract class DataBuffer<T> implements Releasable, Iterable<T>
{
    protected final DataHolder IC;
    
    protected DataBuffer(final DataHolder ic) {
        this.IC = ic;
        if (this.IC != null) {
            this.IC.e(this);
        }
    }
    
    @Deprecated
    public final void close() {
        this.release();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public abstract T get(final int p0);
    
    public int getCount() {
        if (this.IC == null) {
            return 0;
        }
        return this.IC.getCount();
    }
    
    public Bundle gz() {
        return this.IC.gz();
    }
    
    @Deprecated
    public boolean isClosed() {
        return this.IC == null || this.IC.isClosed();
    }
    
    @Override
    public Iterator<T> iterator() {
        return new c<T>(this);
    }
    
    @Override
    public void release() {
        if (this.IC != null) {
            this.IC.close();
        }
    }
    
    public Iterator<T> singleRefIterator() {
        return new h<T>(this);
    }
}
