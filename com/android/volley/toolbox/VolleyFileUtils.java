// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.Closeable;

public class VolleyFileUtils
{
    private static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    private static long copy(final InputStream inputStream, final OutputStream outputStream) {
        long n = 0L;
        final byte[] array = new byte[4096];
        while (true) {
            final int read = inputStream.read(array);
            if (-1 == read) {
                break;
            }
            outputStream.write(array, 0, read);
            n += read;
        }
        return n;
    }
    
    private static FileInputStream openInputStream(final File file) {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        }
        if (!file.canRead()) {
            throw new IOException("File '" + file + "' cannot be read");
        }
        return new FileInputStream(file);
    }
    
    private static byte[] readFileToByteArray(final File file) {
        Closeable openInputStream = null;
        try {
            return toByteArray((InputStream)(openInputStream = openInputStream(file)));
        }
        finally {
            closeQuietly(openInputStream);
        }
    }
    
    public static byte[] readFileUrlToByteArray(final String s) {
        return readFileToByteArray(new File(s.replaceFirst("file://", "")));
    }
    
    private static byte[] toByteArray(final InputStream inputStream) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
