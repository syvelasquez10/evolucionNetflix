// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

import com.netflix.msl.keyx.KeyRequestData;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.keyx.AsymmetricWrappedExchange$RequestData$Mechanism;
import java.security.KeyPairGenerator;
import com.netflix.msl.keyx.AsymmetricWrappedExchange$RequestData;

public class AsymmetricWrappedKeyRequestProvider implements KeyRequestDataProvider<AsymmetricWrappedExchange$RequestData>
{
    private static final String KEY_PAIR_ID = "keyx-keypairid";
    
    @Override
    public AsymmetricWrappedExchange$RequestData get() {
        try {
            final KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
            instance.initialize(2048);
            final KeyPair generateKeyPair = instance.generateKeyPair();
            return new AsymmetricWrappedExchange$RequestData("keyx-keypairid", AsymmetricWrappedExchange$RequestData$Mechanism.RSA, generateKeyPair.getPublic(), generateKeyPair.getPrivate());
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException("RSA algorithm not found.", ex);
        }
    }
}
