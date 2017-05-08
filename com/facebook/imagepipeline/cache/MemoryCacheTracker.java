// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

public interface MemoryCacheTracker
{
    void onCacheHit();
    
    void onCacheMiss();
    
    void onCachePut();
}
