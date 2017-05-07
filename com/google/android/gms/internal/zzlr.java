// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.InputStream;

public final class zzlr
{
    public static long zza(final InputStream inputStream, final OutputStream outputStream) {
        return zza(inputStream, outputStream, false);
    }
    
    public static long zza(final InputStream inputStream, final OutputStream outputStream, final boolean b) {
        return zza(inputStream, outputStream, b, 1024);
    }
    
    public static long zza(final InputStream inputStream, final OutputStream outputStream, final boolean b, int read) {
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
                zzb(inputStream);
                zzb(outputStream);
            }
        }
        if (b) {
            zzb(inputStream);
            zzb(outputStream);
        }
        return n;
    }
    
    public static void zzb(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
}
