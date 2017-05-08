// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

public class MemoryCacheParams
{
    public final int maxCacheEntries;
    public final int maxCacheEntrySize;
    public final int maxCacheSize;
    public final int maxEvictionQueueEntries;
    public final int maxEvictionQueueSize;
    
    public MemoryCacheParams(final int maxCacheSize, final int maxCacheEntries, final int maxEvictionQueueSize, final int maxEvictionQueueEntries, final int maxCacheEntrySize) {
        this.maxCacheSize = maxCacheSize;
        this.maxCacheEntries = maxCacheEntries;
        this.maxEvictionQueueSize = maxEvictionQueueSize;
        this.maxEvictionQueueEntries = maxEvictionQueueEntries;
        this.maxCacheEntrySize = maxCacheEntrySize;
    }
}
