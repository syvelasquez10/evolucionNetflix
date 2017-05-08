// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.common;

public class NoOpCacheErrorLogger implements CacheErrorLogger
{
    private static NoOpCacheErrorLogger sInstance;
    
    static {
        NoOpCacheErrorLogger.sInstance = null;
    }
    
    public static NoOpCacheErrorLogger getInstance() {
        synchronized (NoOpCacheErrorLogger.class) {
            if (NoOpCacheErrorLogger.sInstance == null) {
                NoOpCacheErrorLogger.sInstance = new NoOpCacheErrorLogger();
            }
            return NoOpCacheErrorLogger.sInstance;
        }
    }
    
    @Override
    public void logError(final CacheErrorLogger$CacheErrorCategory cacheErrorLogger$CacheErrorCategory, final Class<?> clazz, final String s, final Throwable t) {
    }
}
