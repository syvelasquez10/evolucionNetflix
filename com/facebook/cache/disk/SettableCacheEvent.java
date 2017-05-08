// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import java.io.IOException;
import com.facebook.cache.common.CacheEventListener$EvictionReason;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.CacheEvent;

public class SettableCacheEvent implements CacheEvent
{
    private CacheKey mCacheKey;
    private long mCacheLimit;
    private long mCacheSize;
    private CacheEventListener$EvictionReason mEvictionReason;
    private IOException mException;
    private long mItemSize;
    private String mResourceId;
    
    public SettableCacheEvent setCacheKey(final CacheKey mCacheKey) {
        this.mCacheKey = mCacheKey;
        return this;
    }
    
    public SettableCacheEvent setCacheLimit(final long mCacheLimit) {
        this.mCacheLimit = mCacheLimit;
        return this;
    }
    
    public SettableCacheEvent setCacheSize(final long mCacheSize) {
        this.mCacheSize = mCacheSize;
        return this;
    }
    
    public SettableCacheEvent setEvictionReason(final CacheEventListener$EvictionReason mEvictionReason) {
        this.mEvictionReason = mEvictionReason;
        return this;
    }
    
    public SettableCacheEvent setException(final IOException mException) {
        this.mException = mException;
        return this;
    }
    
    public SettableCacheEvent setItemSize(final long mItemSize) {
        this.mItemSize = mItemSize;
        return this;
    }
    
    public SettableCacheEvent setResourceId(final String mResourceId) {
        this.mResourceId = mResourceId;
        return this;
    }
}
