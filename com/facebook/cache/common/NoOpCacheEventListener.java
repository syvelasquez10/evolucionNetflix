// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.common;

public class NoOpCacheEventListener implements CacheEventListener
{
    private static NoOpCacheEventListener sInstance;
    
    static {
        NoOpCacheEventListener.sInstance = null;
    }
    
    public static NoOpCacheEventListener getInstance() {
        synchronized (NoOpCacheEventListener.class) {
            if (NoOpCacheEventListener.sInstance == null) {
                NoOpCacheEventListener.sInstance = new NoOpCacheEventListener();
            }
            return NoOpCacheEventListener.sInstance;
        }
    }
    
    @Override
    public void onEviction(final CacheEvent cacheEvent) {
    }
    
    @Override
    public void onHit(final CacheEvent cacheEvent) {
    }
    
    @Override
    public void onMiss(final CacheEvent cacheEvent) {
    }
    
    @Override
    public void onReadException(final CacheEvent cacheEvent) {
    }
    
    @Override
    public void onWriteAttempt(final CacheEvent cacheEvent) {
    }
    
    @Override
    public void onWriteException(final CacheEvent cacheEvent) {
    }
    
    @Override
    public void onWriteSuccess(final CacheEvent cacheEvent) {
    }
}
