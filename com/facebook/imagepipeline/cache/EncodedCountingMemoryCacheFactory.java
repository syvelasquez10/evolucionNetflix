// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.internal.Supplier;

public class EncodedCountingMemoryCacheFactory
{
    public static CountingMemoryCache<CacheKey, PooledByteBuffer> get(final Supplier<MemoryCacheParams> supplier, final MemoryTrimmableRegistry memoryTrimmableRegistry) {
        final CountingMemoryCache<CacheKey, PooledByteBuffer> countingMemoryCache = new CountingMemoryCache<CacheKey, PooledByteBuffer>(new EncodedCountingMemoryCacheFactory$1(), new NativeMemoryCacheTrimStrategy(), supplier);
        memoryTrimmableRegistry.registerMemoryTrimmable(countingMemoryCache);
        return countingMemoryCache;
    }
}
