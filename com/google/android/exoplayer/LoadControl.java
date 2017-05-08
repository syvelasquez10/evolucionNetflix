// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.upstream.Allocator;

public interface LoadControl
{
    Allocator getAllocator();
    
    void register(final Object p0, final int p1);
    
    void trimAllocator();
    
    void unregister(final Object p0);
    
    boolean update(final Object p0, final long p1, final long p2, final boolean p3);
}
