// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import javax.crypto.SecretKey;

public class NullDerivationKeyRepository implements DerivationKeyRepository
{
    @Override
    public void addDerivationKey(final byte[] array, final SecretKey secretKey) {
    }
    
    @Override
    public SecretKey getDerivationKey(final byte[] array) {
        return null;
    }
    
    @Override
    public void removeDerivationKey(final byte[] array) {
    }
}
