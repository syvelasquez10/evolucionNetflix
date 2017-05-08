// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.esn;

import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;

public class WidevineL3PhoneEsnProvider extends EsnCdmProvider
{
    public WidevineL3PhoneEsnProvider(final DrmManager drmManager) {
        super(drmManager, DeviceCategory.PHONE);
        Log.d("ESN", "Widevine L3 phone ESN Provider created...");
    }
    
    @Override
    protected CryptoProvider getCryptoProvider() {
        return CryptoProvider.WIDEVINE_L3;
    }
}
