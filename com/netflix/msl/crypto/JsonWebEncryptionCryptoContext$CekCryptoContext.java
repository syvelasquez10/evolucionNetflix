// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;

public abstract class JsonWebEncryptionCryptoContext$CekCryptoContext implements ICryptoContext
{
    private final JsonWebEncryptionCryptoContext$Algorithm algo;
    
    protected JsonWebEncryptionCryptoContext$CekCryptoContext(final JsonWebEncryptionCryptoContext$Algorithm algo) {
        this.algo = algo;
    }
    
    JsonWebEncryptionCryptoContext$Algorithm getAlgorithm() {
        return this.algo;
    }
    
    @Override
    public byte[] sign(final byte[] array) {
        throw new MslCryptoException(MslError.SIGN_NOT_SUPPORTED);
    }
    
    @Override
    public byte[] unwrap(final byte[] array) {
        throw new MslCryptoException(MslError.UNWRAP_NOT_SUPPORTED);
    }
    
    @Override
    public boolean verify(final byte[] array, final byte[] array2) {
        throw new MslCryptoException(MslError.VERIFY_NOT_SUPPORTED);
    }
    
    @Override
    public byte[] wrap(final byte[] array) {
        throw new MslCryptoException(MslError.WRAP_NOT_SUPPORTED);
    }
}
