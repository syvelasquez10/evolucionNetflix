// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import javax.crypto.SecretKey;

class DiffieHellmanExchange$SessionKeys
{
    public final SecretKey encryptionKey;
    public final SecretKey hmacKey;
    
    public DiffieHellmanExchange$SessionKeys(final SecretKey encryptionKey, final SecretKey hmacKey) {
        this.encryptionKey = encryptionKey;
        this.hmacKey = hmacKey;
    }
}
