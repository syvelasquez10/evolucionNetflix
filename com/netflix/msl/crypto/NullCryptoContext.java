// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

public class NullCryptoContext implements ICryptoContext
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
        return array;
    }
    
    @Override
    public boolean verify(final byte[] array, final byte[] array2) {
        return true;
    }
    
    @Override
    public byte[] wrap(final byte[] array) {
        return array;
    }
}
