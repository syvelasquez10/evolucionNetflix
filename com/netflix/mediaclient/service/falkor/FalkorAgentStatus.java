// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.NetflixStatus;

public class FalkorAgentStatus extends NetflixStatus
{
    private final boolean wasAllDataLocalToCache;
    
    public FalkorAgentStatus(final StatusCode statusCode, final boolean wasAllDataLocalToCache) {
        super(statusCode);
        this.wasAllDataLocalToCache = wasAllDataLocalToCache;
    }
    
    public boolean wasAllDataLocalToCache() {
        return this.wasAllDataLocalToCache;
    }
}
