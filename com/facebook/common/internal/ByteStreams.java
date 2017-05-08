// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.internal;

import java.io.OutputStream;
import java.io.InputStream;

public final class ByteStreams
{
    public static long copy(final InputStream inputStream, final OutputStream outputStream) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(outputStream);
        final byte[] array = new byte[4096];
        long n = 0L;
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
            n += read;
        }
        return n;
    }
    
    public static int read(final InputStream inputStream, final byte[] array, final int n, final int n2) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(array);
        if (n2 < 0) {
            throw new IndexOutOfBoundsException("len is negative");
        }
        int i;
        int read;
        for (i = 0; i < n2; i += read) {
            read = inputStream.read(array, n + i, n2 - i);
            if (read == -1) {
                break;
            }
        }
        return i;
    }
}
