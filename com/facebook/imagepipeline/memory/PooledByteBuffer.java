// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import java.io.Closeable;

public interface PooledByteBuffer extends Closeable
{
    boolean isClosed();
    
    byte read(final int p0);
    
    void read(final int p0, final byte[] p1, final int p2, final int p3);
    
    int size();
}
