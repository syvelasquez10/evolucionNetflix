// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import javax.crypto.IllegalBlockSizeException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.security.InvalidKeyException;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import com.netflix.msl.MslInternalException;
import java.security.Key;
import com.netflix.msl.crypto.CryptoCache;
import javax.crypto.SecretKey;
import com.netflix.msl.crypto.ICryptoContext;

public class JsonWebKeyLadderExchange$AesKwJwkCryptoContext extends JsonWebKeyLadderExchange$JwkCryptoContext
{
    private static final String A128_KW_TRANSFORM = "AESWrap";
    private static final int AES_KW_BLOCK_SIZE = 8;
    private static final byte SPACE = 32;
    private final ICryptoContext cryptoContext;
    private final SecretKey key;
    
    public JsonWebKeyLadderExchange$AesKwJwkCryptoContext(final ICryptoContext cryptoContext) {
        this.key = null;
        this.cryptoContext = cryptoContext;
    }
    
    public JsonWebKeyLadderExchange$AesKwJwkCryptoContext(final SecretKey key) {
        if (!key.getAlgorithm().equals("AES")) {
            throw new IllegalArgumentException("Secret key must be an AES key.");
        }
        this.key = key;
        this.cryptoContext = null;
    }
    
    @Override
    public byte[] unwrap(byte[] encoded) {
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
    public byte[] wrap(byte[] wrap) {
        final int n = wrap.length % 8;
        Label_0049: {
            if (n != 0) {
                break Label_0049;
            }
            while (true) {
                if (this.key == null) {
                    return this.cryptoContext.wrap(wrap);
                }
                try {
                    final Cipher cipher = CryptoCache.getCipher("AESWrap");
                    cipher.init(3, this.key);
                    wrap = cipher.wrap(new SecretKeySpec(wrap, "AES"));
                    return wrap;
                    final int n2 = 8 - n;
                    final byte[] array = new byte[wrap.length + n2];
                    array[0] = 123;
                    final int n3 = n2 + 1;
                    Arrays.fill(array, 1, n3, (byte)32);
                    System.arraycopy(wrap, 1, array, n3, wrap.length - 1);
                    wrap = array;
                    continue;
                }
                catch (NoSuchPaddingException ex) {
                    throw new MslInternalException("Unsupported padding exception.", ex);
                }
                catch (NoSuchAlgorithmException ex2) {
                    throw new MslInternalException("Invalid cipher algorithm specified.", ex2);
                }
                catch (IllegalArgumentException ex3) {
                    throw new MslInternalException("Zero-length plaintext provided.", ex3);
                }
                catch (InvalidKeyException ex4) {
                    throw new MslCryptoException(MslError.INVALID_SYMMETRIC_KEY, ex4);
                }
                catch (IllegalBlockSizeException ex5) {
                    throw new MslCryptoException(MslError.PLAINTEXT_ILLEGAL_BLOCK_SIZE, "not expected when padding is specified", ex5);
                }
                break;
            }
        }
        return this.cryptoContext.wrap(wrap);
    }
}
