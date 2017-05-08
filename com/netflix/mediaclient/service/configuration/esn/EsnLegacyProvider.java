// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.esn;

import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;

public class EsnLegacyProvider extends BaseEsnProvider
{
    @Override
    public void destroy() {
    }
    
    @Override
    protected String findDeviceId(final Context context) {
        String s;
        if ((s = BaseEsnProvider.getIMEA(context)) == null) {
            s = BaseEsnProvider.getMacAddress(context);
        }
        String androidId;
        if ((androidId = s) == null) {
            androidId = BaseEsnProvider.getAndroidId(context);
        }
        if (androidId == null) {
            Log.w("ESN", "Device ID not found");
            return "UKNOWN";
        }
        if ("000000000000000".equalsIgnoreCase(androidId)) {
            Log.w("ESN", "Emulator");
            return "1012UAR71QB0A91";
        }
        return StringUtils.replaceWhiteSpace(androidId, EsnLegacyProvider.DELIM);
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
