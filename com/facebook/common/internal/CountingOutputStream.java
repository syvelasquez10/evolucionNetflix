// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.internal;

import java.io.OutputStream;
import java.io.FilterOutputStream;

public class CountingOutputStream extends FilterOutputStream
{
    private long mCount;
    
    public CountingOutputStream(final OutputStream outputStream) {
        super(outputStream);
        this.mCount = 0L;
    }
    
    @Override
    public void close() {
        this.out.close();
    }
    
    public long getCount() {
        return this.mCount;
    }
    
    @Override
    public void write(final int n) {
        this.out.write(n);
        ++this.mCount;
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        this.out.write(array, n, n2);
        this.mCount += n2;
    }
}
