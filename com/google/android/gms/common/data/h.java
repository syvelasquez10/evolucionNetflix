// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class h<T> extends c<T>
{
    private T Kk;
    
    public h(final DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }
    
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.JP);
        }
        ++this.JP;
        if (this.JP == 0) {
            this.Kk = this.JO.get(0);
            if (!(this.Kk instanceof d)) {
                throw new IllegalStateException("DataBuffer reference of type " + this.Kk.getClass() + " is not movable");
            }
        }
        else {
            ((d)this.Kk).ap(this.JP);
        }
        return this.Kk;
    }
}
