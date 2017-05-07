// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import android.os.Bundle;

public abstract class DataBuffer<T> implements Iterable<T>
{
    protected final DataHolder nE;
    
    protected DataBuffer(final DataHolder ne) {
        this.nE = ne;
        if (this.nE != null) {
            this.nE.c(this);
        }
    }
    
    public void close() {
        if (this.nE != null) {
            this.nE.close();
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public abstract T get(final int p0);
    
    public int getCount() {
        if (this.nE == null) {
            return 0;
        }
        return this.nE.getCount();
    }
    
    public Bundle getMetadata() {
        return this.nE.getMetadata();
    }
    
    public boolean isClosed() {
        return this.nE == null || this.nE.isClosed();
    }
    
    @Override
    public Iterator<T> iterator() {
        return new a<T>(this);
    }
}
