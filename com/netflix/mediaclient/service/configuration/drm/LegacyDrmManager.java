// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

public final class LegacyDrmManager implements DrmManager
{
    private DrmManager$DrmReadyCallback mCallback;
    
    LegacyDrmManager(final DrmManager$DrmReadyCallback mCallback) {
        this.mCallback = mCallback;
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    public byte[] getDeviceId() {
        return null;
    }
    
    @Override
    public String getDeviceType() {
        return null;
    }
    
    @Override
    public int getDrmType() {
        return 0;
    }
    
    @Override
    public void init() {
        this.mCallback.drmReady();
    }
}
