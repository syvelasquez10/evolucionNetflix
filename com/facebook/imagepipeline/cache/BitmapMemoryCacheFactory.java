// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.cache.common.CacheKey;

public class BitmapMemoryCacheFactory
{
    public static MemoryCache<CacheKey, CloseableImage> get(final CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache, final ImageCacheStatsTracker imageCacheStatsTracker) {
        imageCacheStatsTracker.registerBitmapMemoryCache(countingMemoryCache);
        return new InstrumentedMemoryCache<CacheKey, CloseableImage>(countingMemoryCache, new BitmapMemoryCacheFactory$1(imageCacheStatsTracker));
    }
}
