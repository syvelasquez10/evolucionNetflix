// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import javax.crypto.Cipher;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import com.netflix.msl.MslInternalException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.OAEPParameterSpec;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import java.security.PublicKey;
import java.security.PrivateKey;

public class JsonWebEncryptionCryptoContext$RsaOaepCryptoContext extends JsonWebEncryptionCryptoContext$CekCryptoContext
{
    private static final String RSA_OAEP_TRANSFORM = "RSA/ECB/OAEPPadding";
    protected final PrivateKey privateKey;
    protected final PublicKey publicKey;
    
    public JsonWebEncryptionCryptoContext$RsaOaepCryptoContext(final PrivateKey privateKey, final PublicKey publicKey) {
        super(JsonWebEncryptionCryptoContext$Algorithm.RSA_OAEP);
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    @Override
    public byte[] decrypt(byte[] doFinal) {
        if (this.privateKey == null) {
            throw new MslCryptoException(MslError.DECRYPT_NOT_SUPPORTED, "no private key");
        }
        final NoSuchPaddingException ex = null;
        while (true) {
            try {
                final Cipher cipher = CryptoCache.getCipher("RSA/ECB/OAEPPadding");
                cipher.init(2, this.privateKey, OAEPParameterSpec.DEFAULT);
                doFinal = cipher.doFinal(doFinal);
                if (false) {
                    CryptoCache.resetCipher("RSA/ECB/OAEPPadding");
                }
                return doFinal;
            }
            catch (NoSuchPaddingException ex) {
                try {
                    throw new MslInternalException("Unsupported padding exception.", ex);
                }
                finally {}
                if (ex != null) {
                    CryptoCache.resetCipher("RSA/ECB/OAEPPadding");
                }
                throw;
            }
            catch (NoSuchAlgorithmException ex) {
                try {
                    throw new MslInternalException("Invalid cipher algorithm specified.", ex);
                }
                finally {}
            }
            catch (InvalidKeyException ex) {
                try {
                    throw new MslCryptoException(MslError.INVALID_PRIVATE_KEY, ex);
                }
                finally {}
            }
            catch (IllegalBlockSizeException ex) {
                try {
                    throw new MslCryptoException(MslError.CIPHERTEXT_ILLEGAL_BLOCK_SIZE, ex);
                }
                finally {}
            }
            catch (BadPaddingException ex) {
                try {
                    throw new MslCryptoException(MslError.CIPHERTEXT_BAD_PADDING, ex);
                }
                finally {}
            }
            catch (InvalidAlgorithmParameterException ex) {
                try {
                    throw new MslCryptoException(MslError.INVALID_ALGORITHM_PARAMS, ex);
                }
                finally {}
            }
            catch (RuntimeException ex) {
                try {
                    throw ex;
                }
                finally {}
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    @Override
    public byte[] encrypt(byte[] doFinal) {
        if (this.publicKey == null) {
            throw new MslCryptoException(MslError.ENCRYPT_NOT_SUPPORTED, "no public key");
        }
        final NoSuchPaddingException ex = null;
        while (true) {
            try {
                final Cipher cipher = CryptoCache.getCipher("RSA/ECB/OAEPPadding");
                cipher.init(1, this.publicKey, OAEPParameterSpec.DEFAULT);
                doFinal = cipher.doFinal(doFinal);
                if (false) {
                    CryptoCache.resetCipher("RSA/ECB/OAEPPadding");
                }
                return doFinal;
            }
            catch (NoSuchPaddingException ex) {
                try {
                    throw new MslInternalException("Unsupported padding exception.", ex);
                }
                finally {}
                if (ex != null) {
                    CryptoCache.resetCipher("RSA/ECB/OAEPPadding");
                }
                throw;
            }
            catch (NoSuchAlgorithmException ex) {
                try {
                    throw new MslInternalException("Invalid cipher algorithm specified.", ex);
                }
                finally {}
            }
            catch (InvalidKeyException ex) {
                try {
                    throw new MslCryptoException(MslError.INVALID_PUBLIC_KEY, ex);
                }
                finally {}
            }
            catch (IllegalBlockSizeException ex) {
                try {
                    throw new MslCryptoException(MslError.PLAINTEXT_ILLEGAL_BLOCK_SIZE, "not expected when padding is specified", ex);
                }
                finally {}
            }
            catch (BadPaddingException ex) {
                try {
                    throw new MslCryptoException(MslError.PLAINTEXT_BAD_PADDING, "not expected when encrypting", ex);
                }
                finally {}
            }
            catch (InvalidAlgorithmParameterException ex) {
                try {
                    throw new MslCryptoException(MslError.INVALID_ALGORITHM_PARAMS, ex);
                }
                finally {}
            }
            catch (RuntimeException ex) {
                try {
                    throw ex;
                }
                finally {}
            }
            finally {
                continue;
            }
            break;
        }
    }
}
