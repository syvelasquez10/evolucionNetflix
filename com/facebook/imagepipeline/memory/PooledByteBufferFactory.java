// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import java.io.InputStream;

public interface PooledByteBufferFactory
{
    PooledByteBuffer newByteBuffer(final InputStream p0);
    
    PooledByteBuffer newByteBuffer(final InputStream p0, final int p1);
    
    PooledByteBuffer newByteBuffer(final byte[] p0);
    
    PooledByteBufferOutputStream newOutputStream();
    
    PooledByteBufferOutputStream newOutputStream(final int p0);
}
