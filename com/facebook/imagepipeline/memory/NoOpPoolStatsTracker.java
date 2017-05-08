// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

public class NoOpPoolStatsTracker implements PoolStatsTracker
{
    private static NoOpPoolStatsTracker sInstance;
    
    static {
        NoOpPoolStatsTracker.sInstance = null;
    }
    
    public static NoOpPoolStatsTracker getInstance() {
        synchronized (NoOpPoolStatsTracker.class) {
            if (NoOpPoolStatsTracker.sInstance == null) {
                NoOpPoolStatsTracker.sInstance = new NoOpPoolStatsTracker();
            }
            return NoOpPoolStatsTracker.sInstance;
        }
    }
    
    @Override
    public void onAlloc(final int n) {
    }
    
    @Override
    public void onFree(final int n) {
    }
    
    @Override
    public void onHardCapReached() {
    }
    
    @Override
    public void onSoftCapReached() {
    }
    
    @Override
    public void onValueRelease(final int n) {
    }
    
    @Override
    public void onValueReuse(final int n) {
    }
    
    @Override
    public void setBasePool(final BasePool basePool) {
    }
}
