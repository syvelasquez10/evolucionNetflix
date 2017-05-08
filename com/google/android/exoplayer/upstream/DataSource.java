// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

public interface DataSource
{
    void close();
    
    long open(final DataSpec p0);
    
    int read(final byte[] p0, final int p1, final int p2);
}
