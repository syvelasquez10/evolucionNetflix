// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import com.netflix.android.org.json.JSONObject;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManager$CryptoSession;
import com.netflix.msl.keyx.WidevineKeyRequestData;

public class AndroidWidevineKeyRequestData extends WidevineKeyRequestData
{
    private final CryptoManager$CryptoSession keyRequestDataCryptoSession;
    
    public AndroidWidevineKeyRequestData(final JSONObject jsonObject) {
        super(jsonObject);
        throw new IllegalArgumentException("JSON constructor is not supported by client! This should never be called on client!");
    }
    
    public AndroidWidevineKeyRequestData(final CryptoManager$CryptoSession keyRequestDataCryptoSession) {
        super(keyRequestDataCryptoSession.getKeyRequestDataAsString());
        this.keyRequestDataCryptoSession = keyRequestDataCryptoSession;
    }
    
    public CryptoManager$CryptoSession getKeyRequestDataCryptoSession() {
        return this.keyRequestDataCryptoSession;
    }
}
