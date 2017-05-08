// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import com.netflix.mediaclient.util.CryptoUtils;
import android.util.Base64;

public class CryptoManager$CryptoSession
{
    public byte[] keyRequestData;
    public CryptoManager$KeyId keySetId;
    public byte[] sessionId;
    
    public String getKeyRequestDataAsString() {
        if (this.keyRequestData == null) {
            return null;
        }
        return Base64.encodeToString(this.keyRequestData, 2);
    }
    
    public boolean isPending() {
        return this.keySetId == null && this.keyRequestData != null;
    }
    
    @Override
    public String toString() {
        return "CryptoSession{keySetId=" + this.keySetId + ", sessionId=" + CryptoUtils.encodeToString(this.sessionId) + ", keyRequestData=" + this.getKeyRequestDataAsString() + '}';
    }
}
