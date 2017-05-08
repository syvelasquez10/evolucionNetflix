// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.crypto.ICryptoContext;

public interface WrapCryptoContextRepository
{
    void addCryptoContext(final byte[] p0, final ICryptoContext p1);
    
    ICryptoContext getCryptoContext(final byte[] p0);
    
    void removeCryptoContext(final byte[] p0);
}
