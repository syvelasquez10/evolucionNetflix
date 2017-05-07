// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface IManifestCache
{
    void cacheFlush();
    
    void cachePause();
    
    void cacheResume();
    
    void cacheSchedule(final IManifestCache$CacheScheduleRequest[] p0);
    
    void setCacheManifestType(final int p0);
    
    void setMaxCacheByteSize(final int p0);
    
    void setMaxCacheSize(final int p0);
}
