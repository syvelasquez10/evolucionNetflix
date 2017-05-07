// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.NoSuchElementException;
import com.google.android.gms.internal.fq;
import java.util.Iterator;

public final class a<T> implements Iterator<T>
{
    private int BC;
    private final DataBuffer<T> mDataBuffer;
    
    public a(final DataBuffer<T> dataBuffer) {
        this.mDataBuffer = fq.f(dataBuffer);
        this.BC = -1;
    }
    
    @Override
    public boolean hasNext() {
        return this.BC < this.mDataBuffer.getCount() - 1;
    }
    
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.BC);
        }
        final DataBuffer<T> mDataBuffer = this.mDataBuffer;
        final int bc = this.BC + 1;
        this.BC = bc;
        return mDataBuffer.get(bc);
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
