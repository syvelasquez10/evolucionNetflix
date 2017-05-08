// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

public class NoOpImageCacheStatsTracker implements ImageCacheStatsTracker
{
    private static NoOpImageCacheStatsTracker sInstance;
    
    static {
        NoOpImageCacheStatsTracker.sInstance = null;
    }
    
    public static NoOpImageCacheStatsTracker getInstance() {
        synchronized (NoOpImageCacheStatsTracker.class) {
            if (NoOpImageCacheStatsTracker.sInstance == null) {
                NoOpImageCacheStatsTracker.sInstance = new NoOpImageCacheStatsTracker();
            }
            return NoOpImageCacheStatsTracker.sInstance;
        }
    }
    
    @Override
    public void onBitmapCacheHit() {
    }
    
    @Override
    public void onBitmapCacheMiss() {
    }
    
    @Override
    public void onBitmapCachePut() {
    }
    
    @Override
    public void onDiskCacheGetFail() {
    }
    
    @Override
    public void onDiskCacheHit() {
    }
    
    @Override
    public void onDiskCacheMiss() {
    }
    
    @Override
    public void onMemoryCacheHit() {
    }
    
    @Override
    public void onMemoryCacheMiss() {
    }
    
    @Override
    public void onMemoryCachePut() {
    }
    
    @Override
    public void onStagingAreaHit() {
    }
    
    @Override
    public void onStagingAreaMiss() {
    }
    
    @Override
    public void registerBitmapMemoryCache(final CountingMemoryCache<?, ?> countingMemoryCache) {
    }
    
    @Override
    public void registerEncodedMemoryCache(final CountingMemoryCache<?, ?> countingMemoryCache) {
    }
}
