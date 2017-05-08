// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

final class BitmapMemoryCacheFactory$1 implements MemoryCacheTracker
{
    final /* synthetic */ ImageCacheStatsTracker val$imageCacheStatsTracker;
    
    BitmapMemoryCacheFactory$1(final ImageCacheStatsTracker val$imageCacheStatsTracker) {
        this.val$imageCacheStatsTracker = val$imageCacheStatsTracker;
    }
    
    @Override
    public void onCacheHit() {
        this.val$imageCacheStatsTracker.onBitmapCacheHit();
    }
    
    @Override
    public void onCacheMiss() {
        this.val$imageCacheStatsTracker.onBitmapCacheMiss();
    }
    
    @Override
    public void onCachePut() {
        this.val$imageCacheStatsTracker.onBitmapCachePut();
    }
}
