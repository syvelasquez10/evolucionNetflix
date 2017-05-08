// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import javax.crypto.SecretKey;

public interface DerivationKeyRepository
{
    void addDerivationKey(final byte[] p0, final SecretKey p1);
    
    SecretKey getDerivationKey(final byte[] p0);
    
    void removeDerivationKey(final byte[] p0);
}
