// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import com.netflix.msl.util.MslUtils;
import java.util.Arrays;
import javax.crypto.Mac;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import javax.crypto.Cipher;
import java.util.Random;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.MslEncodingException;
import com.netflix.android.org.json.JSONException;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.MslConstants;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import javax.crypto.SecretKey;
import com.netflix.msl.util.MslContext;

public class SymmetricCryptoContext implements ICryptoContext
{
    private static final byte[] AESKW_AIV;
    private static final String AESKW_ALGO = "AES";
    private static final int AESKW_BLOCK_SIZE = 8;
    private static final String AESKW_TRANSFORM = "AES/ECB/NoPadding";
    private static final String AES_ALGO = "AES";
    private static final int AES_IV_SIZE = 16;
    private static final String AES_TRANSFORM = "AES/CBC/PKCS5Padding";
    private static final String HMAC_SHA256_ALGO = "HmacSHA256";
    protected final MslContext ctx;
    protected final SecretKey encryptionKey;
    protected final String id;
    protected final SecretKey signatureKey;
    protected final SecretKey wrappingKey;
    
    static {
        AESKW_AIV = new byte[] { -90, -90, -90, -90, -90, -90, -90, -90 };
    }
    
    public SymmetricCryptoContext(final MslContext ctx, final String id, final SecretKey encryptionKey, final SecretKey signatureKey, final SecretKey wrappingKey) {
        if (encryptionKey != null && !encryptionKey.getAlgorithm().equals("AES")) {
            throw new IllegalArgumentException("Encryption key must be an AES key.");
        }
        if (signatureKey != null && !signatureKey.getAlgorithm().equals("HmacSHA256") && !signatureKey.getAlgorithm().equals("AESCmac")) {
            throw new IllegalArgumentException("Encryption key must be an HmacSHA256 or AESCmac key.");
        }
        if (wrappingKey != null && !wrappingKey.getAlgorithm().equals("AES")) {
            throw new IllegalArgumentException("Encryption key must be an AES key.");
        }
        this.ctx = ctx;
        this.id = id;
        this.encryptionKey = encryptionKey;
        this.signatureKey = signatureKey;
        this.wrappingKey = wrappingKey;
    }
    
    private static byte[] lsb(final int n, final byte[] array) {
        final int length = array.length;
        final byte[] array2 = new byte[n];
        for (int i = 0; i < n; ++i) {
            array2[i] = array[length - n + i];
        }
        return array2;
    }
    
    private static byte[] msb(final int n, final byte[] array) {
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    private static void xor(final byte[] array, final long n) {
        array[0] ^= (byte)(n >>> 56);
        array[1] ^= (byte)(n >>> 48);
        array[2] ^= (byte)(n >>> 40);
        array[3] ^= (byte)(n >>> 32);
        array[4] ^= (byte)(n >>> 24);
        array[5] ^= (byte)(n >>> 16);
        array[6] ^= (byte)(n >>> 8);
        array[7] ^= (byte)n;
    }
    
    @Override
    public byte[] decrypt(final byte[] array) {
        if (this.encryptionKey == null) {
            throw new MslCryptoException(MslError.DECRYPT_NOT_SUPPORTED, "no encryption/decryption key");
        }
        try {
            if (!new MslCiphertextEnvelope(new JSONObject(new String(array, MslConstants.DEFAULT_CHARSET)), MslCiphertextEnvelope$Version.V1).getKeyId().equals(this.id)) {
                throw new MslCryptoException(MslError.ENVELOPE_KEY_ID_MISMATCH);
            }
            goto Label_0087;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new MslCryptoException(MslError.INSUFFICIENT_CIPHERTEXT, ex);
        }
        catch (JSONException ex2) {
            throw new MslCryptoException(MslError.CIPHERTEXT_ENVELOPE_PARSE_ERROR, ex2);
        }
        catch (MslEncodingException ex3) {
            throw new MslCryptoException(MslError.CIPHERTEXT_ENVELOPE_PARSE_ERROR, ex3);
        }
        catch (NoSuchAlgorithmException ex4) {
            throw new MslInternalException("Invalid cipher algorithm specified.", ex4);
        }
        catch (NoSuchPaddingException ex5) {
            throw new MslInternalException("Unsupported padding exception.", ex5);
        }
        catch (InvalidKeyException ex6) {
            throw new MslCryptoException(MslError.INVALID_ENCRYPTION_KEY, ex6);
        }
        catch (InvalidAlgorithmParameterException ex7) {
            throw new MslCryptoException(MslError.INVALID_ALGORITHM_PARAMS, ex7);
        }
        catch (IllegalBlockSizeException ex8) {
            throw new MslCryptoException(MslError.CIPHERTEXT_ILLEGAL_BLOCK_SIZE, ex8);
        }
        catch (BadPaddingException ex9) {
            throw new MslCryptoException(MslError.CIPHERTEXT_BAD_PADDING, ex9);
        }
    }
    
    @Override
    public byte[] encrypt(byte[] doFinal) {
        if (this.encryptionKey == null) {
            throw new MslCryptoException(MslError.ENCRYPT_NOT_SUPPORTED, "no encryption/decryption key");
        }
        try {
            final Random random = this.ctx.getRandom();
            final byte[] array = new byte[16];
            random.nextBytes(array);
            if (doFinal.length != 0) {
                final Cipher cipher = CryptoCache.getCipher("AES/CBC/PKCS5Padding");
                cipher.init(1, this.encryptionKey, new IvParameterSpec(array));
                doFinal = cipher.doFinal(doFinal);
            }
            else {
                doFinal = new byte[0];
            }
            return new MslCiphertextEnvelope(this.id, array, doFinal).toJSONString().getBytes(MslConstants.DEFAULT_CHARSET);
        }
        catch (NoSuchPaddingException ex) {
            throw new MslInternalException("Unsupported padding exception.", ex);
        }
        catch (NoSuchAlgorithmException ex2) {
            throw new MslInternalException("Invalid cipher algorithm specified.", ex2);
        }
        catch (InvalidKeyException ex3) {
            throw new MslCryptoException(MslError.INVALID_ENCRYPTION_KEY, ex3);
        }
        catch (InvalidAlgorithmParameterException ex4) {
            throw new MslCryptoException(MslError.INVALID_ALGORITHM_PARAMS, ex4);
        }
        catch (IllegalBlockSizeException ex5) {
            throw new MslCryptoException(MslError.PLAINTEXT_ILLEGAL_BLOCK_SIZE, "not expected when padding is specified", ex5);
        }
        catch (BadPaddingException ex6) {
            throw new MslCryptoException(MslError.PLAINTEXT_BAD_PADDING, "not expected when encrypting", ex6);
        }
    }
    
    @Override
    public byte[] sign(byte[] doFinal) {
        if (this.signatureKey == null) {
            throw new MslCryptoException(MslError.SIGN_NOT_SUPPORTED, "No signature key.");
        }
        try {
            if (this.signatureKey.getAlgorithm().equals("HmacSHA256")) {
                final Mac mac = CryptoCache.getMac("HmacSHA256");
                mac.init(this.signatureKey);
                doFinal = mac.doFinal(doFinal);
            }
            else {
                if (!this.signatureKey.getAlgorithm().equals("AESCmac")) {
                    goto Label_0161;
                }
                final KeyParameter keyParameter = new KeyParameter(this.signatureKey.getEncoded());
                final CMac cMac = new CMac((BlockCipher)new AESEngine());
                cMac.init((CipherParameters)keyParameter);
                cMac.update(doFinal, 0, doFinal.length);
                doFinal = new byte[cMac.getMacSize()];
                cMac.doFinal(doFinal, 0);
            }
            return new MslSignatureEnvelope(doFinal).getBytes();
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException("Invalid MAC algorithm specified.", ex);
        }
        catch (InvalidKeyException ex2) {
            throw new MslCryptoException(MslError.INVALID_HMAC_KEY, ex2);
        }
    }
    
    @Override
    public byte[] unwrap(byte[] msb) {
        if (this.wrappingKey == null) {
            throw new MslCryptoException(MslError.UNWRAP_NOT_SUPPORTED, "no wrap/unwrap key");
        }
        if (msb.length % 8 != 0) {
            throw new MslCryptoException(MslError.CIPHERTEXT_ILLEGAL_BLOCK_SIZE, "data.length " + msb.length);
        }
        while (true) {
        Label_0218_Outer:
            while (true) {
                int n2 = 0;
            Label_0115_Outer:
                while (true) {
                    while (true) {
                        int n;
                        try {
                            final Cipher cipher = CryptoCache.getCipher("AES/ECB/NoPadding");
                            cipher.init(2, this.wrappingKey);
                            final byte[] copy = Arrays.copyOf(msb, 8);
                            final byte[] copyOfRange = Arrays.copyOfRange(msb, copy.length, msb.length);
                            n = (msb.length - 8) / 8;
                            n2 = 5;
                            msb = copy;
                            break Label_0337;
                            Label_0240: {
                                throw new MslCryptoException(MslError.UNWRAP_ERROR, "initial value " + Arrays.toString(msb));
                            }
                            // iftrue(Label_0240:, !MslUtils.safeEquals(msb, SymmetricCryptoContext.AESKW_AIV) || copyOfRange.length % 8 != 0)
                            return copyOfRange;
                            // iftrue(Label_0347:, n3 < 1)
                            final int n3;
                            xor(msb, n * n2 + n3);
                            final byte[] copyOfRange2 = Arrays.copyOfRange(copyOfRange, (n3 - 1) * 8, n3 * 8);
                            final byte[] copy2 = Arrays.copyOf(msb, msb.length + copyOfRange2.length);
                            System.arraycopy(copyOfRange2, 0, copy2, msb.length, copyOfRange2.length);
                            final byte[] doFinal = cipher.doFinal(copy2);
                            msb = msb(8, doFinal);
                            System.arraycopy(lsb(8, doFinal), 0, copyOfRange, (n3 - 1) * 8, 8);
                            --n3;
                            continue;
                        }
                        catch (NoSuchPaddingException ex) {
                            throw new MslInternalException("Unsupported padding exception.", ex);
                        }
                        catch (NoSuchAlgorithmException ex2) {
                            throw new MslInternalException("Invalid cipher algorithm specified.", ex2);
                        }
                        catch (InvalidKeyException ex3) {
                            throw new MslCryptoException(MslError.INVALID_WRAPPING_KEY, ex3);
                        }
                        catch (IllegalBlockSizeException ex4) {
                            throw new MslCryptoException(MslError.CIPHERTEXT_ILLEGAL_BLOCK_SIZE, ex4);
                        }
                        catch (BadPaddingException ex5) {
                            throw new MslCryptoException(MslError.CIPHERTEXT_BAD_PADDING, ex5);
                        }
                        if (n2 >= 0) {
                            final int n3 = n;
                            continue;
                        }
                        break;
                    }
                    continue Label_0115_Outer;
                }
                Label_0347: {
                    --n2;
                }
                continue Label_0218_Outer;
            }
        }
    }
    
    @Override
    public boolean verify(byte[] doFinal, final byte[] array) {
        if (this.signatureKey == null) {
            throw new MslCryptoException(MslError.VERIFY_NOT_SUPPORTED, "No signature key.");
        }
        try {
            final MslSignatureEnvelope parse = MslSignatureEnvelope.parse(array);
            if (this.signatureKey.getAlgorithm().equals("HmacSHA256")) {
                final Mac mac = CryptoCache.getMac("HmacSHA256");
                mac.init(this.signatureKey);
                doFinal = mac.doFinal(doFinal);
            }
            else {
                if (!this.signatureKey.getAlgorithm().equals("AESCmac")) {
                    goto Label_0165;
                }
                final KeyParameter keyParameter = new KeyParameter(this.signatureKey.getEncoded());
                final CMac cMac = new CMac((BlockCipher)new AESEngine());
                cMac.init((CipherParameters)keyParameter);
                cMac.update(doFinal, 0, doFinal.length);
                doFinal = new byte[cMac.getMacSize()];
                cMac.doFinal(doFinal, 0);
            }
            return MslUtils.safeEquals(doFinal, parse.getSignature());
        }
        catch (MslEncodingException ex) {
            throw new MslCryptoException(MslError.SIGNATURE_ENVELOPE_PARSE_ERROR, ex);
        }
        catch (NoSuchAlgorithmException ex2) {
            throw new MslInternalException("Invalid MAC algorithm specified.", ex2);
        }
        catch (InvalidKeyException ex3) {
            throw new MslCryptoException(MslError.INVALID_HMAC_KEY, ex3);
        }
    }
    
    @Override
    public byte[] wrap(byte[] msb) {
        if (this.wrappingKey == null) {
            throw new MslCryptoException(MslError.WRAP_NOT_SUPPORTED, "no wrap/unwrap key");
        }
        if (msb.length % 8 != 0) {
            throw new MslCryptoException(MslError.PLAINTEXT_ILLEGAL_BLOCK_SIZE, "data.length " + msb.length);
        }
        while (true) {
            final byte[] array = SymmetricCryptoContext.AESKW_AIV.clone();
            final byte[] array2 = msb.clone();
            Cipher cipher;
            int n;
            int n2;
            int n3;
            byte[] array3;
            byte[] copyOfRange;
            byte[] copy;
            byte[] doFinal;
            Label_0114_Outer:Label_0218_Outer:
            while (true) {
                while (true) {
                    while (true) {
                        try {
                            cipher = CryptoCache.getCipher("AES/ECB/NoPadding");
                            cipher.init(1, this.wrappingKey);
                            n = array2.length / 8;
                            n2 = 0;
                            msb = array;
                            break Label_0322;
                            // iftrue(Label_0333:, n3 > n)
                            Block_6: {
                                break Block_6;
                                array3 = new byte[msb.length + array2.length];
                                System.arraycopy(msb, 0, array3, 0, msb.length);
                                System.arraycopy(array2, 0, array3, msb.length, array2.length);
                                return array3;
                            }
                            copyOfRange = Arrays.copyOfRange(array2, (n3 - 1) * 8, n3 * 8);
                            copy = Arrays.copyOf(msb, msb.length + copyOfRange.length);
                            System.arraycopy(copyOfRange, 0, copy, msb.length, copyOfRange.length);
                            doFinal = cipher.doFinal(copy);
                            msb = msb(8, doFinal);
                            xor(msb, n * n2 + n3);
                            System.arraycopy(lsb(8, doFinal), 0, array2, (n3 - 1) * 8, 8);
                            ++n3;
                            continue Label_0218_Outer;
                        }
                        catch (NoSuchAlgorithmException ex) {
                            throw new MslInternalException("Invalid cipher algorithm specified.", ex);
                        }
                        catch (NoSuchPaddingException ex2) {
                            throw new MslInternalException("Unsupported padding exception.", ex2);
                        }
                        catch (InvalidKeyException ex3) {
                            throw new MslCryptoException(MslError.INVALID_WRAPPING_KEY, ex3);
                        }
                        catch (IllegalBlockSizeException ex4) {
                            throw new MslCryptoException(MslError.PLAINTEXT_ILLEGAL_BLOCK_SIZE, "not expected when padding is no padding", ex4);
                        }
                        catch (BadPaddingException ex5) {
                            throw new MslCryptoException(MslError.PLAINTEXT_BAD_PADDING, "not expected when encrypting", ex5);
                        }
                        if (n2 < 6) {
                            n3 = 1;
                            continue Label_0218_Outer;
                        }
                        break;
                    }
                    continue;
                }
                Label_0333: {
                    ++n2;
                }
                continue Label_0114_Outer;
            }
        }
    }
}
