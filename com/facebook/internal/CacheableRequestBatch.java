// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import com.facebook.Request;
import com.facebook.RequestBatch;

public class CacheableRequestBatch extends RequestBatch
{
    private String cacheKey;
    private boolean forceRoundTrip;
    
    public CacheableRequestBatch() {
    }
    
    public CacheableRequestBatch(final Request... array) {
        super(array);
    }
    
    public final String getCacheKeyOverride() {
        return this.cacheKey;
    }
    
    public final boolean getForceRoundTrip() {
        return this.forceRoundTrip;
    }
    
    public final void setCacheKeyOverride(final String cacheKey) {
        this.cacheKey = cacheKey;
    }
    
    public final void setForceRoundTrip(final boolean forceRoundTrip) {
        this.forceRoundTrip = forceRoundTrip;
    }
}
