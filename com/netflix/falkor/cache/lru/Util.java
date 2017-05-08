// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache.lru;

import java.io.StringWriter;
import java.io.Reader;
import java.io.IOException;
import java.io.File;
import java.io.Closeable;
import java.nio.charset.Charset;

final class Util
{
    static final Charset US_ASCII;
    static final Charset UTF_8;
    
    static {
        US_ASCII = Charset.forName("US-ASCII");
        UTF_8 = Charset.forName("UTF-8");
    }
    
    static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {}
    }
    
    static void deleteContents(File file) {
        final File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (int length = listFiles.length, i = 0; i < length; ++i) {
            file = listFiles[i];
            if (file.isDirectory()) {
                deleteContents(file);
            }
            if (!file.delete()) {
                throw new IOException("failed to delete file: " + file);
            }
        }
    }
    
    static String readFully(final Reader reader) {
        try {
            final StringWriter stringWriter = new StringWriter();
            final char[] array = new char[1024];
            while (true) {
                final int read = reader.read(array);
                if (read == -1) {
                    break;
                }
                stringWriter.write(array, 0, read);
            }
        }
        finally {
            reader.close();
        }
        final StringWriter stringWriter2;
        final String string = stringWriter2.toString();
        reader.close();
        return string;
    }
}
