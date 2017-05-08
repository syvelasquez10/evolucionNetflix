// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.security.Signature;
import java.security.SignatureException;
import javax.crypto.Cipher;
import java.security.InvalidAlgorithmParameterException;
import com.netflix.msl.MslEncodingException;
import com.netflix.android.org.json.JSONException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import java.security.Key;
import com.netflix.msl.MslInternalException;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.MslConstants;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.spec.AlgorithmParameterSpec;

public abstract class AsymmetricCryptoContext implements ICryptoContext
{
    protected static final String NULL_OP = "nullOp";
    private final String algo;
    protected final String id;
    private final AlgorithmParameterSpec params;
    protected final PrivateKey privateKey;
    protected final PublicKey publicKey;
    private final String transform;
    
    protected AsymmetricCryptoContext(final String id, final PrivateKey privateKey, final PublicKey publicKey, final String transform, final AlgorithmParameterSpec params, final String algo) {
        this.id = id;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.transform = transform;
        this.params = params;
        this.algo = algo;
    }
    
    @Override
    public byte[] decrypt(byte[] doFinal) {
        if (!"nullOp".equals(this.transform)) {
            if (this.privateKey == null) {
                throw new MslCryptoException(MslError.DECRYPT_NOT_SUPPORTED, "no private key");
            }
            Object o = null;
            Label_0116: {
                while (true) {
                    try {
                        if (!new MslCiphertextEnvelope(new JSONObject(new String(doFinal, MslConstants.DEFAULT_CHARSET)), MslCiphertextEnvelope$Version.V1).getKeyId().equals(this.id)) {
                            throw new MslCryptoException(MslError.ENVELOPE_KEY_ID_MISMATCH);
                        }
                        break Label_0116;
                    }
                    catch (NoSuchPaddingException o) {
                        try {
                            throw new MslInternalException("Unsupported padding exception.", (Throwable)o);
                        }
                        finally {}
                        if (o != null) {
                            CryptoCache.resetCipher(this.transform);
                        }
                        throw doFinal;
                        final Cipher cipher = CryptoCache.getCipher(this.transform);
                        cipher.init(2, this.privateKey, this.params);
                        doFinal = cipher.doFinal(((MslCiphertextEnvelope)(Object)doFinal).getCiphertext());
                        o = (doFinal = doFinal);
                        // iftrue(Label_0012:, !false)
                        CryptoCache.resetCipher(this.transform);
                        return (byte[])o;
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
                    catch (JSONException o) {
                        try {
                            throw new MslCryptoException(MslError.CIPHERTEXT_ENVELOPE_PARSE_ERROR, (Throwable)o);
                        }
                        finally {}
                    }
                    catch (MslEncodingException o) {
                        try {
                            throw new MslCryptoException(MslError.CIPHERTEXT_ENVELOPE_PARSE_ERROR, (Throwable)o);
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
        }
        Label_0012: {
            return doFinal;
        }
    }
    
    @Override
    public byte[] encrypt(byte[] array) {
        Object o = null;
        if (!"nullOp".equals(this.transform)) {
            if (this.publicKey == null) {
                throw new MslCryptoException(MslError.ENCRYPT_NOT_SUPPORTED, "no public key");
            }
            while (true) {
                try {
                    final Cipher cipher = CryptoCache.getCipher(this.transform);
                    cipher.init(1, this.publicKey, this.params);
                    array = cipher.doFinal(array);
                    array = new MslCiphertextEnvelope(this.id, null, array).toJSONString().getBytes(MslConstants.DEFAULT_CHARSET);
                    o = (array = array);
                    if (false) {
                        CryptoCache.resetCipher(this.transform);
                        return (byte[])o;
                    }
                }
                catch (NoSuchPaddingException o) {
                    try {
                        throw new MslInternalException("Unsupported padding exception.", (Throwable)o);
                    }
                    finally {}
                    if (o != null) {
                        CryptoCache.resetCipher(this.transform);
                    }
                    throw array;
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
        return array;
    }
    
    @Override
    public byte[] sign(byte[] bytes) {
        if ("nullOp".equals(this.algo)) {
            return new byte[0];
        }
        if (this.privateKey == null) {
            throw new MslCryptoException(MslError.SIGN_NOT_SUPPORTED, "no private key.");
        }
        try {
            final Signature signature = CryptoCache.getSignature(this.algo);
            signature.initSign(this.privateKey);
            signature.update(bytes);
            bytes = new MslSignatureEnvelope(signature.sign()).getBytes();
            return bytes;
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException("Invalid signature algorithm specified.", ex);
        }
        catch (InvalidKeyException ex2) {
            throw new MslCryptoException(MslError.INVALID_PRIVATE_KEY, ex2);
        }
        catch (SignatureException ex3) {
            throw new MslCryptoException(MslError.SIGNATURE_ERROR, ex3);
        }
    }
    
    @Override
    public byte[] unwrap(final byte[] array) {
        throw new MslCryptoException(MslError.UNWRAP_NOT_SUPPORTED);
    }
    
    @Override
    public boolean verify(final byte[] array, final byte[] array2) {
        if ("nullOp".equals(this.algo)) {
            return true;
        }
        if (this.publicKey == null) {
            throw new MslCryptoException(MslError.VERIFY_NOT_SUPPORTED, "no public key.");
        }
        try {
            final MslSignatureEnvelope parse = MslSignatureEnvelope.parse(array2);
            final Signature signature = CryptoCache.getSignature(this.algo);
            signature.initVerify(this.publicKey);
            signature.update(array);
            return signature.verify(parse.getSignature());
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException("Invalid signature algorithm specified.", ex);
        }
        catch (InvalidKeyException ex2) {
            throw new MslCryptoException(MslError.INVALID_PUBLIC_KEY, ex2);
        }
        catch (SignatureException ex3) {
            throw new MslCryptoException(MslError.SIGNATURE_ERROR, ex3);
        }
        catch (MslEncodingException ex4) {
            throw new MslCryptoException(MslError.SIGNATURE_ENVELOPE_PARSE_ERROR, ex4);
        }
    }
    
    @Override
    public byte[] wrap(final byte[] array) {
        throw new MslCryptoException(MslError.WRAP_NOT_SUPPORTED);
    }
}
