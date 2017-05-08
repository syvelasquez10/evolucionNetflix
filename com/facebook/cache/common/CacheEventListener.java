// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.common;

public interface CacheEventListener
{
    void onEviction(final CacheEvent p0);
    
    void onHit(final CacheEvent p0);
    
    void onMiss(final CacheEvent p0);
    
    void onReadException(final CacheEvent p0);
    
    void onWriteAttempt(final CacheEvent p0);
    
    void onWriteException(final CacheEvent p0);
    
    void onWriteSuccess(final CacheEvent p0);
}
