// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.cache.common.CacheKey;

public class EncodedMemoryCacheFactory
{
    public static MemoryCache<CacheKey, PooledByteBuffer> get(final CountingMemoryCache<CacheKey, PooledByteBuffer> countingMemoryCache, final ImageCacheStatsTracker imageCacheStatsTracker) {
        imageCacheStatsTracker.registerEncodedMemoryCache(countingMemoryCache);
        return new InstrumentedMemoryCache<CacheKey, PooledByteBuffer>(countingMemoryCache, new EncodedMemoryCacheFactory$1(imageCacheStatsTracker));
    }
}
