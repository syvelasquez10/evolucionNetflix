// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.security.InvalidKeyException;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import com.netflix.msl.MslInternalException;
import java.security.Key;
import javax.crypto.SecretKey;

public class JsonWebEncryptionCryptoContext$AesKwCryptoContext extends JsonWebEncryptionCryptoContext$CekCryptoContext
{
    private static final String A128_KW_TRANSFORM = "AESWrap";
    private final ICryptoContext cryptoContext;
    private final SecretKey key;
    
    public JsonWebEncryptionCryptoContext$AesKwCryptoContext(final ICryptoContext cryptoContext) {
        super(JsonWebEncryptionCryptoContext$Algorithm.A128KW);
        this.key = null;
        this.cryptoContext = cryptoContext;
    }
    
    public JsonWebEncryptionCryptoContext$AesKwCryptoContext(final SecretKey key) {
        super(JsonWebEncryptionCryptoContext$Algorithm.A128KW);
        if (!key.getAlgorithm().equals("AES")) {
            throw new IllegalArgumentException("Secret key must be an AES key.");
        }
        this.key = key;
        this.cryptoContext = null;
    }
    
    @Override
    public byte[] decrypt(byte[] encoded) {
        if (this.key != null) {
            try {
                final Cipher cipher = CryptoCache.getCipher("AESWrap");
                cipher.init(4, this.key);
                encoded = cipher.unwrap(encoded, "AES", 3).getEncoded();
                return encoded;
            }
            catch (NoSuchPaddingException ex) {
                throw new MslInternalException("Unsupported padding exception.", ex);
            }
            catch (NoSuchAlgorithmException ex2) {
                throw new MslInternalException("Invalid cipher algorithm specified.", ex2);
            }
            catch (InvalidKeyException ex3) {
                throw new MslCryptoException(MslError.INVALID_SYMMETRIC_KEY, ex3);
            }
        }
        return this.cryptoContext.unwrap(encoded);
    }
    
    @Override
    public byte[] encrypt(byte[] wrap) {
        if (this.key != null) {
            try {
                final Cipher cipher = CryptoCache.getCipher("AESWrap");
                cipher.init(3, this.key);
                wrap = cipher.wrap(new SecretKeySpec(wrap, "AES"));
                return wrap;
            }
            catch (NoSuchPaddingException ex) {
                throw new MslInternalException("Unsupported padding exception.", ex);
            }
            catch (NoSuchAlgorithmException ex2) {
                throw new MslInternalException("Invalid cipher algorithm specified.", ex2);
            }
            catch (IllegalArgumentException ex3) {
                throw new MslInternalException("Invalid content encryption key provided.", ex3);
            }
            catch (InvalidKeyException ex4) {
                throw new MslCryptoException(MslError.INVALID_SYMMETRIC_KEY, ex4);
            }
            catch (IllegalBlockSizeException ex5) {
                throw new MslCryptoException(MslError.PLAINTEXT_ILLEGAL_BLOCK_SIZE, "not expected when padding is specified", ex5);
            }
        }
        return this.cryptoContext.wrap(wrap);
    }
}
