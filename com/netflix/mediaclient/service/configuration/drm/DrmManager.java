// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;

public interface DrmManager
{
    void destroy();
    
    CryptoProvider getCryptoProvider();
    
    byte[] getDeviceId();
    
    String getDeviceType();
    
    void init();
}
