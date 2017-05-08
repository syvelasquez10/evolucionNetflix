// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.FileInputStream;
import java.io.File;
import java.io.InputStreamReader;
import android.content.res.AssetManager;
import java.io.InputStream;
import android.content.Context;

public final class dz
{
    public static String a(Context open, final String s) {
        final AssetManager assets = open.getAssets();
        open = null;
        try {
            return a((InputStream)(open = (Context)assets.open(s)));
        }
        finally {
            if (open != null) {
                ((InputStream)open).close();
            }
        }
    }
    
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
            final Object o2;
            final Object o = o2;
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
                fileInputStream.close();
                Label_0031: {
                    throw;
                }
            }
            finally {
                continue;
            }
            break;
        }
    }
}
