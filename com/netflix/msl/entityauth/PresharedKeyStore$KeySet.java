// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import javax.crypto.SecretKey;

public class PresharedKeyStore$KeySet
{
    public final SecretKey encryptionKey;
    public final SecretKey hmacKey;
    public final SecretKey wrappingKey;
    
    public PresharedKeyStore$KeySet(final SecretKey encryptionKey, final SecretKey hmacKey, final SecretKey wrappingKey) {
        this.encryptionKey = encryptionKey;
        this.hmacKey = hmacKey;
        this.wrappingKey = wrappingKey;
    }
}
