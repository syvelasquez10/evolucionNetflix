// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class IoUtil
{
    public static byte[] readBytes(final InputStream inputStream, int read) {
        if (read < 128) {
            throw new IllegalArgumentException("Buffer size should be at least 128");
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[read];
        while (true) {
            read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(array, 0, read);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
