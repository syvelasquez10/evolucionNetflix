// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

public interface ImageCacheStatsTracker
{
    void onBitmapCacheHit();
    
    void onBitmapCacheMiss();
    
    void onBitmapCachePut();
    
    void onDiskCacheGetFail();
    
    void onDiskCacheHit();
    
    void onDiskCacheMiss();
    
    void onMemoryCacheHit();
    
    void onMemoryCacheMiss();
    
    void onMemoryCachePut();
    
    void onStagingAreaHit();
    
    void onStagingAreaMiss();
    
    void registerBitmapMemoryCache(final CountingMemoryCache<?, ?> p0);
    
    void registerEncodedMemoryCache(final CountingMemoryCache<?, ?> p0);
}
