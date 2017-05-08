// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import com.netflix.mediaclient.Log;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.io.DataInputStream;
import java.io.UnsupportedEncodingException;

public final class ParserUtils
{
    protected static final String TAG = "nf_subtitles_imv2";
    public static final String UTF8_CHARSET = "UTF-8";
    
    public static final String decode(final byte[] array) {
        return decode(array, "UTF-8");
    }
    
    public static final String decode(final byte[] array, final String s) {
        try {
            return new String(array, s);
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static boolean isType(final byte[] array, final byte[] array2) {
        if (array != null && array2 != null && array.length == array2.length) {
            for (int i = 0; i < array2.length; ++i) {
                if (array2[i] != array[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static byte[] readByteArray(final DataInputStream dataInputStream, final int n) {
        final byte[] array = new byte[n];
        dataInputStream.read(array);
        return array;
    }
    
    public static UUID readUUID(final DataInputStream dataInputStream) {
        final byte[] array = new byte[16];
        dataInputStream.read(array);
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        final UUID uuid = new UUID(wrap.getLong(), wrap.getLong());
        Log.d("nf_subtitles_imv2", "readUUID: " + uuid);
        return uuid;
    }
    
    public static int readUint16(final byte[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Byte array is bad!");
        }
        return readUint16(array, 0);
    }
    
    public static int readUint16(final byte[] array, final int n) {
        return 0x0 | (array[n + 0] << 8 & 0xFF00) | (array[n + 1] & 0xFF);
    }
    
    public static long readUint32(final byte[] array) {
        if (array == null || array.length < 4) {
            throw new IllegalArgumentException("Byte array is bad!");
        }
        return readUint32(array, 0);
    }
    
    public static long readUint32(final byte[] array, final int n) {
        return 0x0L | (array[n + 0] << 24 & 0xFF000000) | (array[n + 1] << 16 & 0xFF0000) | (array[n + 2] << 8 & 0xFF00) | (array[n + 3] & 0xFF);
    }
    
    public static long readUint64(final byte[] array) {
        return readUint64(array, 0);
    }
    
    public static long readUint64(final byte[] array, final int n) {
        long n2 = 0L;
        for (int i = 0; i < 8; ++i) {
            n2 |= (array[i + n] & 0xFF) << 56 - i * 8;
        }
        return n2;
    }
    
    public static short readUint8(final byte b) {
        return (short)(b & 0xFF);
    }
}
