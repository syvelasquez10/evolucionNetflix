// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.esn;

import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;

public class WidevineL1TabletEsnProvider extends EsnCdmProvider
{
    public WidevineL1TabletEsnProvider(final DrmManager drmManager) {
        super(drmManager, DeviceCategory.TABLET);
        Log.d("ESN", "Widevine L1 tablet ESN Provider created...");
    }
    
    @Override
    protected CryptoProvider getCryptoProvider() {
        return CryptoProvider.WIDEVINE_L1;
    }
}
