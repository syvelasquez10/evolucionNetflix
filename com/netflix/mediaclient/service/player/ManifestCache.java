// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import com.netflix.mediaclient.servicemgr.IManifestCache$CacheScheduleRequest;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.servicemgr.IManifestCache;

public class ManifestCache implements IManifestCache
{
    private IMedia mMediaBridgeApi;
    
    ManifestCache(final IMedia mMediaBridgeApi) {
        this.mMediaBridgeApi = mMediaBridgeApi;
    }
    
    @Override
    public void cacheFlush() {
        this.mMediaBridgeApi.cacheFlush();
    }
    
    @Override
    public void cachePause() {
        this.mMediaBridgeApi.cachePause();
    }
    
    @Override
    public void cacheResume() {
        this.mMediaBridgeApi.cacheResume();
    }
    
    @Override
    public void cacheSchedule(final IManifestCache$CacheScheduleRequest[] array) {
        this.mMediaBridgeApi.cacheSchedule(array);
    }
    
    @Override
    public void setCacheManifestType(final int cacheManifestType) {
        this.mMediaBridgeApi.setCacheManifestType(cacheManifestType);
    }
    
    @Override
    public void setMaxCacheByteSize(final int maxCacheByteSize) {
        this.mMediaBridgeApi.setMaxCacheByteSize(maxCacheByteSize);
    }
    
    @Override
    public void setMaxCacheSize(final int maxCacheSize) {
        this.mMediaBridgeApi.setMaxCacheSize(maxCacheSize);
    }
}
