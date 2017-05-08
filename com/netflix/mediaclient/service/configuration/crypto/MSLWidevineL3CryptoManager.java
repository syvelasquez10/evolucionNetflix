// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import com.netflix.mediaclient.util.MediaDrmUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.content.Context;

public class MSLWidevineL3CryptoManager extends MSLWidevineCryptoManager
{
    public MSLWidevineL3CryptoManager(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final CryptoManager$DrmReadyCallback cryptoManager$DrmReadyCallback) {
        super(context, CryptoProvider.WIDEVINE_L3, serviceAgent$ConfigurationAgentInterface, cryptoManager$DrmReadyCallback);
    }
    
    @Override
    protected void setSecurityLevel() {
        Log.d("nf_msl", "Set security level L3...");
        MediaDrmUtils.setSecurityLevelL3(this.mDrm);
    }
}
