// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import com.netflix.msl.MslInternalException;

public class ClientMslCryptoContext implements ICryptoContext
{
    @Override
    public byte[] decrypt(final byte[] array) {
        return array;
    }
    
    @Override
    public byte[] encrypt(final byte[] array) {
        return array;
    }
    
    @Override
    public byte[] sign(final byte[] array) {
        return new byte[0];
    }
    
    @Override
    public byte[] unwrap(final byte[] array) {
        throw new MslInternalException("Unwrap is unsupported by the MSL token crypto context.");
    }
    
    @Override
    public boolean verify(final byte[] array, final byte[] array2) {
        return false;
    }
    
    @Override
    public byte[] wrap(final byte[] array) {
        throw new MslInternalException("Wrap is unsupported by the MSL token crypto context.");
    }
}
