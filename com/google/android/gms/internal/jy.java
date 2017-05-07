// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.os.ParcelFileDescriptor;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.InputStream;

public final class jy
{
    public static long a(final InputStream inputStream, final OutputStream outputStream, final boolean b) {
        return a(inputStream, outputStream, b, 1024);
    }
    
    public static long a(final InputStream inputStream, final OutputStream outputStream, final boolean b, int read) {
        final byte[] array = new byte[read];
        long n = 0L;
        try {
            while (true) {
                read = inputStream.read(array, 0, array.length);
                if (read == -1) {
                    break;
                }
                n += read;
                outputStream.write(array, 0, read);
            }
        }
        finally {
            if (b) {
                b(inputStream);
                b(outputStream);
            }
        }
        if (b) {
            b(inputStream);
            b(outputStream);
        }
        return n;
    }
    
    public static void a(final ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor == null) {
            return;
        }
        try {
            parcelFileDescriptor.close();
        }
        catch (IOException ex) {}
    }
    
    public static byte[] a(final InputStream inputStream, final boolean b) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, byteArrayOutputStream, b);
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void b(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    public static byte[] d(final InputStream inputStream) {
        return a(inputStream, true);
    }
}
