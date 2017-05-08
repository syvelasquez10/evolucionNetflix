// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import javax.crypto.SecretKey;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import java.security.MessageDigest;
import java.math.BigInteger;
import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import com.netflix.mediaclient.Log;
import android.util.Base64;

public final class CryptoUtils
{
    public static final int AES128_BLOCK_SIZE = 16;
    public static final int DES_BLOCK_SIZE = 8;
    private static final int[] DIGITS_POWER;
    private static final String HEX = "0123456789ABCDEF";
    private static final String HMAC_SHA_1 = "HmacSHA1";
    private static final String HMAC_SHA_1_ALT = "HMAC-SHA-1";
    private static final String SHA_256 = "SHA-256";
    private static final String TAG = "nf_crypto";
    private static final int[] doubleDigits;
    
    static {
        doubleDigits = new int[] { 0, 2, 4, 6, 8, 1, 3, 5, 7, 9 };
        DIGITS_POWER = new int[] { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };
    }
    
    private static void appendHex(final StringBuilder sb, final byte b) {
        sb.append("0123456789ABCDEF".charAt(b >> 4 & 0xF)).append("0123456789ABCDEF".charAt(b & 0xF));
    }
    
    public static int calcChecksum(long n, int n2) {
        int n3 = 0;
        final int n4 = 1;
        int i = n2;
        n2 = n4;
        while (i > 0) {
            final int n5 = (int)(n % 10L);
            n /= 10L;
            int n6 = n5;
            if (n2 != 0) {
                n6 = CryptoUtils.doubleDigits[n5];
            }
            n3 += n6;
            if (n2 == 0) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            --i;
        }
        final int n7 = n3 % 10;
        if ((n2 = n7) > 0) {
            n2 = 10 - n7;
        }
        return n2;
    }
    
    public static byte[] decode(final String s) {
        return Base64.decode(s, 0);
    }
    
    public static String decrypt(final String s, final String s2) {
        Log.d("nf_crypto", "Encrypted text " + s2);
        if (s2 == null) {
            Log.e("nf_crypto", "Encrypted is null");
            return null;
        }
        try {
            if (init(s, false) == null) {
                Log.e("nf_crypto", "decrypt: ciper is null!");
                return null;
            }
            goto Label_0087;
        }
        catch (BadPaddingException ex) {
            Log.e("nf_crypto", "EXCEPTION: " + ex);
            return null;
        }
        catch (IllegalBlockSizeException ex2) {
            Log.e("nf_crypto", "EXCEPTION: " + ex2);
            return null;
        }
        catch (UnsupportedEncodingException ex3) {
            Log.e("nf_crypto", "EXCEPTION: " + ex3);
            return null;
        }
        catch (Exception ex4) {
            Log.e("nf_crypto", "EXCEPTION: " + ex4);
            return null;
        }
    }
    
    public static String encodeToString(final byte[] array) {
        return Base64.encodeToString(array, 2);
    }
    
    public static String encrypt(String hex, final String s) {
        final Cipher init = init(hex, true);
        if (init == null) {
            Log.e("nf_crypto", "encrypt: ciper is null!");
            return null;
        }
        if (s == null) {
            Log.e("nf_crypto", "encrypt: cleartext is null!");
            return null;
        }
        try {
            final byte[] bytes = s.getBytes("UTF-8");
            if (bytes == null) {
                Log.e("nf_crypto", "encrypt: utf8 is null!");
            }
            final byte[] doFinal = init.doFinal(bytes);
            if (doFinal == null) {
                Log.e("nf_crypto", "encrypt: enc is null!");
            }
            hex = toHex(doFinal);
            return hex;
        }
        catch (BadPaddingException ex) {
            Log.e("nf_crypto", "EXCEPTION: " + ex);
            return null;
        }
        catch (IllegalBlockSizeException ex2) {
            Log.e("nf_crypto", "EXCEPTION: " + ex2);
            return null;
        }
        catch (UnsupportedEncodingException ex3) {
            Log.e("nf_crypto", "EXCEPTION: " + ex3);
            return null;
        }
        catch (Exception ex4) {
            Log.e("nf_crypto", "EXCEPTION: " + ex4);
            return null;
        }
    }
    
    public static String fromHex(final String s) {
        return new String(toByte(s));
    }
    
    public static String generateOTP(String s, final long n, long n2, final int n3, final boolean b, int n4) {
        final byte[] secretBytes = getSecretBytes(s, n);
        int n5;
        if (b) {
            n5 = n3 + 1;
        }
        else {
            n5 = n3;
        }
        final byte[] array = new byte[8];
        for (int i = array.length - 1; i >= 0; --i) {
            array[i] = (byte)(0xFFL & n2);
            n2 >>= 8;
        }
        final byte[] hmac_sha1 = hmac_sha1(secretBytes, array);
        final byte b2 = hmac_sha1[hmac_sha1.length - 1];
        if (n4 < 0 || n4 >= hmac_sha1.length - 4) {
            n4 = (b2 & 0xF);
        }
        final int n6 = n4 = ((hmac_sha1[n4] & 0x7F) << 24 | (hmac_sha1[n4 + 1] & 0xFF) << 16 | (hmac_sha1[n4 + 2] & 0xFF) << 8 | (hmac_sha1[n4 + 3] & 0xFF)) % CryptoUtils.DIGITS_POWER[n3];
        if (b) {
            n4 = calcChecksum(n6, n3) + n6 * 10;
        }
        for (s = Integer.toString(n4); s.length() < n5; s = "0" + s) {}
        return s;
    }
    
    private static byte[] getSecretBytes(final String s, final long n) {
        int i = 0;
        final byte[] array = new byte[s.length() / 2 + 4];
        final byte[] byteArray = new BigInteger(s, 16).toByteArray();
        System.arraycopy(byteArray, 0, array, 0, byteArray.length);
        while (i < 4) {
            array[byteArray.length + i] = (byte)(n >> (3 - i) * 8 & 0xFFL);
            ++i;
        }
        return array;
    }
    
    public static String hashSHA256(final String s, final String s2) {
        final MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(or(s, s2));
        return toHex(instance.digest());
    }
    
    public static byte[] hmac_sha1(final byte[] array, final byte[] array2) {
        String s = "HmacSHA1";
        while (true) {
            try {
                final Mac mac = Mac.getInstance("HmacSHA1");
                mac.init(new SecretKeySpec(array, s));
                return mac.doFinal(array2);
            }
            catch (NoSuchAlgorithmException ex) {
                s = "HMAC-SHA-1";
                final Mac mac = Mac.getInstance("HMAC-SHA-1");
                continue;
            }
            break;
        }
    }
    
    private static Cipher init(String instance, final boolean b) {
        final byte[] array;
        Object o = array = new byte[8];
        array[0] = -87;
        array[1] = -101;
        array[2] = -56;
        array[3] = 50;
        array[4] = 86;
        array[5] = 52;
        array[6] = -29;
        array[7] = 3;
        try {
            final SecretKey generateSecret = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec(instance.toCharArray(), (byte[])o, 19));
            instance = (String)Cipher.getInstance(generateSecret.getAlgorithm());
            try {
                o = new PBEParameterSpec((byte[])o, 19);
                if (b) {
                    ((Cipher)instance).init(1, generateSecret, (AlgorithmParameterSpec)o);
                    return (Cipher)instance;
                }
                ((Cipher)instance).init(2, generateSecret, (AlgorithmParameterSpec)o);
                return (Cipher)instance;
            }
            catch (InvalidAlgorithmParameterException ex) {}
            catch (Exception ex2) {}
            catch (InvalidKeyException ex3) {}
            catch (NoSuchAlgorithmException ex4) {}
            catch (NoSuchPaddingException ex5) {}
            catch (InvalidKeySpecException ex6) {}
        }
        catch (InvalidKeySpecException ex7) {}
        catch (NoSuchPaddingException ex8) {}
        catch (NoSuchAlgorithmException ex9) {}
        catch (InvalidKeyException ex10) {}
        catch (Exception ex11) {}
        catch (InvalidAlgorithmParameterException o) {
            instance = null;
            goto Label_0118;
        }
    }
    
    private static byte[] or(final String s, final String s2) {
        byte[] bytes3;
        byte[] bytes4;
        if (s.length() >= s2.length()) {
            final byte[] bytes = s.getBytes();
            final byte[] bytes2 = s2.getBytes();
            bytes3 = bytes;
            bytes4 = bytes2;
        }
        else {
            bytes3 = s2.getBytes();
            bytes4 = s.getBytes();
        }
        for (int min = Math.min(bytes3.length, bytes4.length), i = 0; i < min; ++i) {
            bytes3[i] |= bytes4[i];
        }
        return bytes3;
    }
    
    public static byte[] padPerPKCS5Padding(final byte[] array, int n) {
        final byte b = 0;
        if (array == null) {
            throw new IllegalArgumentException("Input array is null!");
        }
        Log.d("nf_crypto", "Array size: " + array.length);
        Log.d("nf_crypto", "Block size: " + n);
        final byte b2 = (byte)(n - array.length % n);
        Log.d("nf_crypto", "Padding: " + b2);
        final byte[] array2 = new byte[array.length + b2];
        n = 0;
        byte b3;
        while (true) {
            b3 = b;
            if (n >= array.length) {
                break;
            }
            array2[n] = array[n];
            ++n;
        }
        while (b3 < b2) {
            array2[array.length + b3] = b2;
            ++b3;
        }
        return array2;
    }
    
    public static byte[] toByte(final String s) {
        final int n = s.length() / 2;
        final byte[] array = new byte[n];
        for (int i = 0; i < n; ++i) {
            array[i] = (byte)(Object)Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16);
        }
        return array;
    }
    
    public static String toHex(final String s) {
        return toHex(s.getBytes());
    }
    
    public static String toHex(final byte[] array) {
        if (array == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder(array.length * 2);
        for (int i = 0; i < array.length; ++i) {
            appendHex(sb, array[i]);
        }
        return sb.toString();
    }
    
    public static byte[] unpadPerPKCS5Padding(final byte[] array, int i) {
        if (array == null || array.length < 1) {
            throw new IllegalArgumentException("Input array is null or 0!");
        }
        i = array[array.length - 1];
        if (Log.isLoggable()) {
            Log.d("nf_crypto", "Remove last " + i + " array elements");
        }
        byte[] array2;
        for (array2 = new byte[array.length - i], i = 0; i < array2.length; ++i) {
            array2[i] = array[i];
        }
        return array2;
    }
}
