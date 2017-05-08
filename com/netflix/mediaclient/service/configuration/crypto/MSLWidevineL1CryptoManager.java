// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.content.Context;

public class MSLWidevineL1CryptoManager extends MSLWidevineCryptoManager
{
    public MSLWidevineL1CryptoManager(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final CryptoManager$DrmReadyCallback cryptoManager$DrmReadyCallback) {
        super(context, CryptoProvider.WIDEVINE_L1, serviceAgent$ConfigurationAgentInterface, cryptoManager$DrmReadyCallback);
    }
    
    @Override
    protected void setSecurityLevel() {
        Log.d("nf_msl", "No need to set security level L1...");
    }
}
