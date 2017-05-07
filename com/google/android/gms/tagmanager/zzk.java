// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

public class zzk
{
    public static byte[] zzet(final String s) {
        final int length = s.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("purported base16 string has odd number of characters");
        }
        final byte[] array = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            final int digit = Character.digit(s.charAt(i), 16);
            final int digit2 = Character.digit(s.charAt(i + 1), 16);
            if (digit == -1 || digit2 == -1) {
                throw new IllegalArgumentException("purported base16 string has illegal char");
            }
            array[i / 2] = (byte)((digit << 4) + digit2);
        }
        return array;
    }
    
    public static String zzi(final byte[] array) {
        final StringBuilder sb = new StringBuilder();
        for (int length = array.length, i = 0; i < length; ++i) {
            final byte b = array[i];
            if ((b & 0xF0) == 0x0) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(b & 0xFF));
        }
        return sb.toString().toUpperCase();
    }
}
