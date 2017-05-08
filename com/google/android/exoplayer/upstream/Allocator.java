// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

public interface Allocator
{
    Allocation allocate();
    
    int getIndividualAllocationLength();
    
    int getTotalBytesAllocated();
    
    void release(final Allocation p0);
    
    void release(final Allocation[] p0);
    
    void trim(final int p0);
}
