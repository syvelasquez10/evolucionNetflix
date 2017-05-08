// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

final class EncodedMemoryCacheFactory$1 implements MemoryCacheTracker
{
    final /* synthetic */ ImageCacheStatsTracker val$imageCacheStatsTracker;
    
    EncodedMemoryCacheFactory$1(final ImageCacheStatsTracker val$imageCacheStatsTracker) {
        this.val$imageCacheStatsTracker = val$imageCacheStatsTracker;
    }
    
    @Override
    public void onCacheHit() {
        this.val$imageCacheStatsTracker.onMemoryCacheHit();
    }
    
    @Override
    public void onCacheMiss() {
        this.val$imageCacheStatsTracker.onMemoryCacheMiss();
    }
    
    @Override
    public void onCachePut() {
        this.val$imageCacheStatsTracker.onMemoryCachePut();
    }
}
