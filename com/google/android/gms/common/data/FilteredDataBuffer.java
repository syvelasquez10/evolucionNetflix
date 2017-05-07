// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.internal.ds;

public abstract class FilteredDataBuffer<T> extends DataBuffer<T>
{
    protected final DataBuffer<T> mDataBuffer;
    
    public FilteredDataBuffer(final DataBuffer<T> mDataBuffer) {
        super(null);
        ds.d(mDataBuffer);
        ds.a(!(mDataBuffer instanceof FilteredDataBuffer), "Not possible to have nested FilteredDataBuffers.");
        this.mDataBuffer = mDataBuffer;
    }
    
    @Override
    public void close() {
        this.mDataBuffer.close();
    }
    
    protected abstract int computeRealPosition(final int p0);
    
    @Override
    public T get(final int n) {
        return this.mDataBuffer.get(this.computeRealPosition(n));
    }
    
    @Override
    public Bundle getMetadata() {
        return this.mDataBuffer.getMetadata();
    }
    
    @Override
    public boolean isClosed() {
        return this.mDataBuffer.isClosed();
    }
}
