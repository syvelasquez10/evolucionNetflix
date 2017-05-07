// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.OutputStream;
import org.json.JSONException;
import java.io.IOException;
import org.json.JSONTokener;
import com.facebook.LoggingBehavior;
import org.json.JSONObject;
import java.io.InputStream;

final class FileLruCache$StreamHeader
{
    static JSONObject readHeader(final InputStream inputStream) {
        final int n = 0;
        if (inputStream.read() != 0) {
            return null;
        }
        int i = 0;
        int n2 = 0;
        while (i < 3) {
            final int read = inputStream.read();
            if (read == -1) {
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
                return null;
            }
            n2 = (n2 << 8) + (read & 0xFF);
            ++i;
        }
        final byte[] array = new byte[n2];
        int read2;
        for (int j = n; j < array.length; j += read2) {
            read2 = inputStream.read(array, j, array.length - j);
            if (read2 < 1) {
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read stopped at " + (Object)j + " when expected " + array.length);
                return null;
            }
        }
        final JSONTokener jsonTokener = new JSONTokener(new String(array));
        try {
            final Object nextValue = jsonTokener.nextValue();
            if (!(nextValue instanceof JSONObject)) {
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: expected JSONObject, got " + ((JSONObject)nextValue).getClass().getCanonicalName());
                return null;
            }
            return (JSONObject)nextValue;
        }
        catch (JSONException ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    static void writeHeader(final OutputStream outputStream, final JSONObject jsonObject) {
        final byte[] bytes = jsonObject.toString().getBytes();
        outputStream.write(0);
        outputStream.write(bytes.length >> 16 & 0xFF);
        outputStream.write(bytes.length >> 8 & 0xFF);
        outputStream.write(bytes.length >> 0 & 0xFF);
        outputStream.write(bytes);
    }
}
