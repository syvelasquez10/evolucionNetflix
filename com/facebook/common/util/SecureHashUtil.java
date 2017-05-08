// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.util;

import java.security.NoSuchAlgorithmException;
import android.util.Base64;
import java.security.MessageDigest;

public class SecureHashUtil
{
    static final byte[] HEX_CHAR_TABLE;
    
    static {
        HEX_CHAR_TABLE = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
    }
    
    public static String makeSHA1HashBase64(final byte[] array) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(array, 0, array.length);
            return Base64.encodeToString(instance.digest(), 11);
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
