// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

public interface DrmManager
{
    public static final int DRM_TYPE_NONE = 0;
    public static final int DRM_TYPE_WIDEVINE = 1;
    
    void destroy();
    
    byte[] getDeviceId();
    
    String getDeviceType();
    
    int getDrmType();
    
    void init();
    
    public interface DrmReadyCallback
    {
        void drmError(final int p0);
        
        void drmReady();
    }
}
