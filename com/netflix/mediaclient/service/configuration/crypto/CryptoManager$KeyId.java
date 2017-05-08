// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import com.netflix.mediaclient.util.CryptoUtils;
import com.netflix.mediaclient.util.StringUtils;

public class CryptoManager$KeyId
{
    private byte[] keyId;
    private String keyIdAsString;
    
    public CryptoManager$KeyId(final String keyIdAsString) {
        if (StringUtils.isEmpty(keyIdAsString)) {
            throw new IllegalStateException("Key Set ID can not be empty!");
        }
        this.keyIdAsString = keyIdAsString;
        this.keyId = CryptoUtils.decode(keyIdAsString);
    }
    
    public CryptoManager$KeyId(final byte[] keyId) {
        if (keyId == null || keyId.length < 0) {
            throw new IllegalStateException("Key Set ID can not be empty!");
        }
        this.keyId = keyId;
        this.keyIdAsString = CryptoUtils.encodeToString(keyId);
    }
    
    public byte[] get() {
        return this.keyId;
    }
    
    public String getAsBase64EncodedString() {
        return this.keyIdAsString;
    }
    
    @Override
    public String toString() {
        return "KeyId{" + this.keyIdAsString + '}';
    }
}
