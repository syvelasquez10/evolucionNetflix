// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.esn;

import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import android.content.Context;
import com.netflix.mediaclient.Log;

public class EsnLegacyMPlusProvider extends BaseEsnProvider
{
    EsnLegacyMPlusProvider() {
        Log.d(EsnLegacyMPlusProvider.class.getSimpleName(), "non wide vine esn without IMEI");
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    protected String findDeviceId(final Context context) {
        return BaseEsnProvider.findFutureDeviceId2(context);
    }
    
    @Override
    protected String findModelId() {
        return BaseEsnProvider.findBaseModelId();
    }
    
    @Override
    public int getCryptoFactoryType() {
        return 1;
    }
    
    @Override
    protected CryptoProvider getCryptoProvider() {
        return CryptoProvider.LEGACY;
    }
}
