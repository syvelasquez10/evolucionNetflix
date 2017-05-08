// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

public interface PoolStatsTracker
{
    void onAlloc(final int p0);
    
    void onFree(final int p0);
    
    void onHardCapReached();
    
    void onSoftCapReached();
    
    void onValueRelease(final int p0);
    
    void onValueReuse(final int p0);
    
    void setBasePool(final BasePool p0);
}
