// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.esn;

import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;

public class EsnCdmNexus7Provider extends BaseEsnProvider
{
    private static final String DEVICE_TYPE_PREFIX = "PRV-";
    private String drmSystemId;
    private String drmUniqueDeviceId;
    
    EsnCdmNexus7Provider(final DrmManager drmManager) {
        if (drmManager == null) {
            throw new IllegalArgumentException("DrmManager is null!");
        }
        final byte[] deviceId = drmManager.getDeviceId();
        this.drmSystemId = drmManager.getDeviceType();
        if (deviceId == null) {
            throw new IllegalArgumentException("MediaDrm.uniqueDeviceId is null! We can not use this ESN implementation!");
        }
        this.drmUniqueDeviceId = new String(deviceId);
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    protected String findDeviceId(final Context context) {
        return this.drmUniqueDeviceId;
    }
    
    @Override
    protected String findModelId() {
        return "PRV-" + this.drmSystemId;
    }
    
    @Override
    public int getCryptoFactoryType() {
        return 2;
    }
    
    @Override
    protected CryptoProvider getCryptoProvider() {
        return CryptoProvider.WIDEVINE_L1;
    }
}
