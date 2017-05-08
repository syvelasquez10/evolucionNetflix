// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.internal.Supplier;

public class BitmapCountingMemoryCacheFactory
{
    public static CountingMemoryCache<CacheKey, CloseableImage> get(final Supplier<MemoryCacheParams> supplier, final MemoryTrimmableRegistry memoryTrimmableRegistry) {
        final CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache = new CountingMemoryCache<CacheKey, CloseableImage>(new BitmapCountingMemoryCacheFactory$1(), new BitmapMemoryCacheTrimStrategy(), supplier);
        memoryTrimmableRegistry.registerMemoryTrimmable(countingMemoryCache);
        return countingMemoryCache;
    }
}
