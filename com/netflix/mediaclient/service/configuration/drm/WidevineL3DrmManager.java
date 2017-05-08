// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.Log;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;

public class WidevineL3DrmManager extends WidevineDrmManager
{
    WidevineL3DrmManager(final Context context, final ServiceAgent$UserAgentInterface serviceAgent$UserAgentInterface, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final ErrorLogging errorLogging, final IErrorHandler errorHandler, final DrmManager$DrmReadyCallback drmManager$DrmReadyCallback) {
        super(context, serviceAgent$UserAgentInterface, serviceAgent$ConfigurationAgentInterface, errorLogging, errorHandler, drmManager$DrmReadyCallback);
    }
    
    public CryptoProvider getCryptoProvider() {
        return CryptoProvider.WIDEVINE_L3;
    }
    
    @Override
    protected void setSecurityLevel() {
        Log.d(WidevineL3DrmManager.TAG, "Setting security level to L3");
        this.drm.setPropertyString("securityLevel", "L3");
    }
}
