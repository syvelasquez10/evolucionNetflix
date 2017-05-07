// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import android.os.Bundle;

public abstract class DataBuffer<T> implements Iterable<T>
{
    protected final DataHolder BB;
    
    protected DataBuffer(final DataHolder bb) {
        this.BB = bb;
        if (this.BB != null) {
            this.BB.c(this);
        }
    }
    
    public void close() {
        if (this.BB != null) {
            this.BB.close();
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public abstract T get(final int p0);
    
    public int getCount() {
        if (this.BB == null) {
            return 0;
        }
        return this.BB.getCount();
    }
    
    public Bundle getMetadata() {
        return this.BB.getMetadata();
    }
    
    public boolean isClosed() {
        return this.BB == null || this.BB.isClosed();
    }
    
    @Override
    public Iterator<T> iterator() {
        return new a<T>(this);
    }
}
