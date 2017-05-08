// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import javax.crypto.Cipher;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import java.security.Key;
import com.netflix.msl.crypto.CryptoCache;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import javax.crypto.spec.OAEPParameterSpec;
import com.netflix.msl.MslInternalException;
import java.security.PublicKey;
import java.security.PrivateKey;
import com.netflix.msl.util.MslContext;
import java.security.spec.AlgorithmParameterSpec;
import com.netflix.msl.crypto.AsymmetricCryptoContext;

class AsymmetricWrappedExchange$RsaWrappingCryptoContext extends AsymmetricCryptoContext
{
    private final AlgorithmParameterSpec wrapParams;
    private final String wrapTransform;
    
    public AsymmetricWrappedExchange$RsaWrappingCryptoContext(final MslContext mslContext, final String s, final PrivateKey privateKey, final PublicKey publicKey, final AsymmetricWrappedExchange$RsaWrappingCryptoContext$Mode asymmetricWrappedExchange$RsaWrappingCryptoContext$Mode) {
        super(s, privateKey, publicKey, "nullOp", null, "nullOp");
        switch (AsymmetricWrappedExchange$1.$SwitchMap$com$netflix$msl$keyx$AsymmetricWrappedExchange$RsaWrappingCryptoContext$Mode[asymmetricWrappedExchange$RsaWrappingCryptoContext$Mode.ordinal()]) {
            default: {
                throw new MslInternalException("RSA wrapping crypto context mode " + asymmetricWrappedExchange$RsaWrappingCryptoContext$Mode + " not supported.");
            }
            case 1: {
                this.wrapTransform = "RSA/ECB/OAEPPadding";
                this.wrapParams = OAEPParameterSpec.DEFAULT;
            }
            case 2: {
                this.wrapTransform = "RSA/ECB/PKCS1Padding";
                this.wrapParams = null;
            }
        }
    }
    
    @Override
    public byte[] unwrap(byte[] doFinal) {
        if (!"nullOp".equals(this.wrapTransform)) {
            if (this.privateKey == null) {
                throw new MslCryptoException(MslError.DECRYPT_NOT_SUPPORTED, "no private key");
            }
            Object o = null;
            while (true) {
                try {
                    final Cipher cipher = CryptoCache.getCipher(this.wrapTransform);
                    cipher.init(2, this.privateKey, this.wrapParams);
                    doFinal = cipher.doFinal(doFinal);
                    o = (doFinal = doFinal);
                    if (false) {
                        CryptoCache.resetCipher(this.wrapTransform);
                        return (byte[])o;
                    }
                }
                catch (NoSuchPaddingException o) {
                    try {
                        throw new MslInternalException("Unsupported padding exception.", (Throwable)o);
                    }
                    finally {}
                    if (o != null) {
                        CryptoCache.resetCipher(this.wrapTransform);
                    }
                    throw doFinal;
                }
                catch (NoSuchAlgorithmException o) {
                    try {
                        throw new MslInternalException("Invalid cipher algorithm specified.", (Throwable)o);
                    }
                    finally {}
                }
                catch (InvalidKeyException o) {
                    try {
                        throw new MslCryptoException(MslError.INVALID_PRIVATE_KEY, (Throwable)o);
                    }
                    finally {}
                }
                catch (IllegalBlockSizeException o) {
                    try {
                        throw new MslCryptoException(MslError.CIPHERTEXT_ILLEGAL_BLOCK_SIZE, (Throwable)o);
                    }
                    finally {}
                }
                catch (BadPaddingException o) {
                    try {
                        throw new MslCryptoException(MslError.CIPHERTEXT_BAD_PADDING, (Throwable)o);
                    }
                    finally {}
                }
                catch (InvalidAlgorithmParameterException o) {
                    try {
                        throw new MslCryptoException(MslError.INVALID_ALGORITHM_PARAMS, (Throwable)o);
                    }
                    finally {}
                }
                catch (RuntimeException o) {
                    try {
                        throw o;
                    }
                    finally {}
                }
                finally {
                    continue;
                }
                break;
            }
        }
        return doFinal;
    }
    
    @Override
    public byte[] wrap(byte[] doFinal) {
        if (!"nullOp".equals(this.wrapTransform)) {
            if (this.publicKey == null) {
                throw new MslCryptoException(MslError.WRAP_NOT_SUPPORTED, "no public key");
            }
            Object o = null;
            while (true) {
                try {
                    final Cipher cipher = CryptoCache.getCipher(this.wrapTransform);
                    cipher.init(1, this.publicKey, this.wrapParams);
                    doFinal = cipher.doFinal(doFinal);
                    o = (doFinal = doFinal);
                    if (false) {
                        CryptoCache.resetCipher(this.wrapTransform);
                        return (byte[])o;
                    }
                }
                catch (NoSuchPaddingException o) {
                    try {
                        throw new MslInternalException("Unsupported padding exception.", (Throwable)o);
                    }
                    finally {}
                    if (o != null) {
                        CryptoCache.resetCipher(this.wrapTransform);
                    }
                    throw doFinal;
                }
                catch (NoSuchAlgorithmException o) {
                    try {
                        throw new MslInternalException("Invalid cipher algorithm specified.", (Throwable)o);
                    }
                    finally {}
                }
                catch (InvalidKeyException o) {
                    try {
                        throw new MslCryptoException(MslError.INVALID_PUBLIC_KEY, (Throwable)o);
                    }
                    finally {}
                }
                catch (IllegalBlockSizeException o) {
                    try {
                        throw new MslCryptoException(MslError.PLAINTEXT_ILLEGAL_BLOCK_SIZE, "not expected when padding is specified", (Throwable)o);
                    }
                    finally {}
                }
                catch (BadPaddingException o) {
                    try {
                        throw new MslCryptoException(MslError.PLAINTEXT_BAD_PADDING, "not expected when encrypting", (Throwable)o);
                    }
                    finally {}
                }
                catch (InvalidAlgorithmParameterException o) {
                    try {
                        throw new MslCryptoException(MslError.INVALID_ALGORITHM_PARAMS, (Throwable)o);
                    }
                    finally {}
                }
                catch (RuntimeException o) {
                    try {
                        throw o;
                    }
                    finally {}
                }
                finally {
                    continue;
                }
                break;
            }
        }
        return doFinal;
    }
}
