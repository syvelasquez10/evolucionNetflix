// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.NoSuchElementException;
import com.google.android.gms.common.internal.n;
import java.util.Iterator;

public class c<T> implements Iterator<T>
{
    protected final DataBuffer<T> JO;
    protected int JP;
    
    public c(final DataBuffer<T> dataBuffer) {
        this.JO = n.i(dataBuffer);
        this.JP = -1;
    }
    
    @Override
    public boolean hasNext() {
        return this.JP < this.JO.getCount() - 1;
    }
    
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.JP);
        }
        final DataBuffer<T> jo = this.JO;
        final int jp = this.JP + 1;
        this.JP = jp;
        return jo.get(jp);
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
