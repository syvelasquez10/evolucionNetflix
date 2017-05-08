// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import javax.crypto.SecretKey;

public class AbstractAuthenticatedDiffieHellmanExchange$SessionKeys
{
    public final SecretKey derivationKey;
    public final SecretKey encryptionKey;
    public final SecretKey hmacKey;
    
    public AbstractAuthenticatedDiffieHellmanExchange$SessionKeys(final SecretKey encryptionKey, final SecretKey hmacKey, final SecretKey derivationKey) {
        this.encryptionKey = encryptionKey;
        this.hmacKey = hmacKey;
        this.derivationKey = derivationKey;
    }
}
