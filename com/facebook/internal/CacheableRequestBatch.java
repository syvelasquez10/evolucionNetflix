// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import com.facebook.RequestBatch;

public class CacheableRequestBatch extends RequestBatch
{
    private String cacheKey;
    private boolean forceRoundTrip;
    
    public final String getCacheKeyOverride() {
        return this.cacheKey;
    }
    
    public final boolean getForceRoundTrip() {
        return this.forceRoundTrip;
    }
}
