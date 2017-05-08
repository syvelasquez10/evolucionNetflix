// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import com.netflix.msl.crypto.ICryptoContext;

public abstract class JsonWebKeyLadderExchange$JwkCryptoContext implements ICryptoContext
{
    @Override
    public byte[] decrypt(final byte[] array) {
        throw new MslCryptoException(MslError.DECRYPT_NOT_SUPPORTED);
    }
    
    @Override
    public byte[] encrypt(final byte[] array) {
        throw new MslCryptoException(MslError.ENCRYPT_NOT_SUPPORTED);
    }
    
    @Override
    public byte[] sign(final byte[] array) {
        throw new MslCryptoException(MslError.SIGN_NOT_SUPPORTED);
    }
    
    @Override
    public boolean verify(final byte[] array, final byte[] array2) {
        throw new MslCryptoException(MslError.VERIFY_NOT_SUPPORTED);
    }
}
