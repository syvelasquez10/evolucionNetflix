// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.concurrent.TimeUnit;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import com.netflix.mediaclient.Log;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

public class AesCencDecryptor
{
    private static final String ALGORITHM = "AES";
    private static final int IV_KEY_LENGTH = 16;
    private static final String TAG = "AseCencDecryptor";
    private static final String TRANSFORMATION = "AES/CTR/NoPadding";
    private Cipher mCipher;
    private SecretKeySpec mKeySpec;
    
    public AesCencDecryptor(final byte[] array) {
        try {
            this.mCipher = Cipher.getInstance("AES/CTR/NoPadding");
            this.mKeySpec = new SecretKeySpec(array, "AES");
        }
        catch (NoSuchPaddingException ex) {}
        catch (IllegalArgumentException ex2) {
            Log.d("AseCencDecryptor", "KeySpec has IllegalArgumentException");
        }
        catch (NoSuchAlgorithmException ex3) {
            goto Label_0029;
        }
    }
    
    private void copyWorkingBuffer(final ByteBuffer byteBuffer, final byte[] array, final int[] array2, final int[] array3, final boolean b) {
        int i = 0;
        int position = byteBuffer.position();
        byteBuffer.mark();
        int n = 0;
        while (i < array3.length) {
            position += array2[i];
            byteBuffer.position(position);
            final int n2 = array3[i];
            if (b) {
                byteBuffer.get(array, n, n2);
            }
            else {
                byteBuffer.put(array, n, n2);
            }
            n += n2;
            ++i;
        }
        byteBuffer.reset();
    }
    
    public boolean decrypt(final ByteBuffer ex, byte[] array, final int[] array2, final int[] array3) {
        int i = 0;
        if (ex == null || array == null || array2 == null || array3 == null) {
            if (Log.isLoggable()) {
                Log.d("AseCencDecryptor", "input is null");
            }
            return false;
        }
        if (array2.length == 0 || array3.length == 0 || array2.length < array3.length || (array.length != 16 && array.length != 8)) {
            if (Log.isLoggable()) {
                Log.d("AseCencDecryptor", "iv length and byte map " + array.length + "," + array2.length + ", " + array3.length);
            }
            return false;
        }
        final long nanoTime = System.nanoTime();
        byte[] array4 = array;
        if (array.length < 16) {
            array4 = new byte[16];
            System.arraycopy(array, 0, array4, 0, array.length);
        }
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(array4);
        try {
            this.mCipher.init(2, this.mKeySpec, ivParameterSpec);
            int n;
            int n2;
            for (n = 0; i < array3.length; ++i, n += n2) {
                n2 = array3[i];
            }
            array = new byte[n];
            this.copyWorkingBuffer((ByteBuffer)ex, array, array2, array3, true);
            this.mCipher.doFinal(array, 0, n, array);
            this.copyWorkingBuffer((ByteBuffer)ex, array, array2, array3, false);
            if (Log.isLoggable()) {
                Log.d("AseCencDecryptor", "decrypt " + n + ", finished in " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - nanoTime) + " us");
            }
            return true;
        }
        catch (InvalidKeyException ex) {
            Log.d("AseCencDecryptor", "fail to decrypt, InvalidKeyException. " + ex);
            return true;
        }
        catch (InvalidAlgorithmParameterException ex) {
            Log.d("AseCencDecryptor", "fail to decrypt, InvalidAlgorithmParameterException." + ex);
            return true;
        }
        catch (ShortBufferException ex) {
            Log.d("AseCencDecryptor", "fail to decrypt, output buffer is too short. ");
            return true;
        }
        catch (IllegalBlockSizeException ex2) {}
        catch (BadPaddingException ex) {
            goto Label_0380;
        }
    }
}
