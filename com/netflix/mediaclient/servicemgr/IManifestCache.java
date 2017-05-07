// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.javabridge.invoke.media.AuthorizationParams;

public interface IManifestCache
{
    void cacheFlush();
    
    void cachePause();
    
    void cacheResume();
    
    void cacheSchedule(final IManifestCache$CacheScheduleRequest[] p0, final AuthorizationParams p1);
    
    void setCacheManifestType(final int p0);
    
    void setMaxCacheByteSize(final int p0);
    
    void setMaxCacheSize(final int p0);
}
