// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.NoSuchElementException;
import com.google.android.gms.internal.eg;
import java.util.Iterator;

public final class a<T> implements Iterator<T>
{
    private final DataBuffer<T> mDataBuffer;
    private int nF;
    
    public a(final DataBuffer<T> dataBuffer) {
        this.mDataBuffer = eg.f(dataBuffer);
        this.nF = -1;
    }
    
    @Override
    public boolean hasNext() {
        return this.nF < this.mDataBuffer.getCount() - 1;
    }
    
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.nF);
        }
        final DataBuffer<T> mDataBuffer = this.mDataBuffer;
        final int nf = this.nF + 1;
        this.nF = nf;
        return mDataBuffer.get(nf);
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
