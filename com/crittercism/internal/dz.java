// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.FileInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.InputStream;

public final class dz
{
    private static String a(final InputStream inputStream) {
        final StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                Label_0048: {
                    try {
                        while (true) {
                            final int read = inputStreamReader.read();
                            if (read == -1) {
                                break Label_0048;
                            }
                            sb.append((char)read);
                        }
                    }
                    finally {}
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    throw;
                }
                inputStreamReader.close();
                return sb.toString();
            }
            finally {
                final InputStreamReader inputStreamReader = null;
                continue;
            }
            break;
        }
    }
    
    public static void a(final File file) {
        if (!file.getAbsolutePath().contains("com.crittercism")) {
            return;
        }
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                a(listFiles[i]);
            }
        }
        file.delete();
    }
    
    public static String b(final File file) {
        FileInputStream fileInputStream;
        try {
            final FileInputStream fileInputStream2;
            fileInputStream = (fileInputStream2 = new FileInputStream(file));
            final String s = a(fileInputStream2);
            final FileInputStream fileInputStream3 = fileInputStream;
            fileInputStream3.close();
            return s;
        }
        finally {
            final Throwable t2;
            final Throwable t = t2;
            fileInputStream = null;
        }
        while (true) {
            try {
                final FileInputStream fileInputStream2 = fileInputStream;
                final String s = a(fileInputStream2);
                final FileInputStream fileInputStream3 = fileInputStream;
                fileInputStream3.close();
                return s;
                // iftrue(Label_0031:, fileInputStream == null)
                final Throwable t;
                Block_6: {
                    break Block_6;
                    Label_0031: {
                        throw t;
                    }
                }
                fileInputStream.close();
                throw t;
            }
            finally {
                continue;
            }
            break;
        }
    }
}
