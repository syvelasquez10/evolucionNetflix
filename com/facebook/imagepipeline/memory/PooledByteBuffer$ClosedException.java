// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

public class PooledByteBuffer$ClosedException extends RuntimeException
{
    public PooledByteBuffer$ClosedException() {
        super("Invalid bytebuf. Already closed");
    }
}
