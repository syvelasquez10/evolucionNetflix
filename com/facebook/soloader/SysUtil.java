// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import android.os.Parcel;
import android.os.Build;
import android.os.Build$VERSION;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

public final class SysUtil
{
    static int copyBytes(final RandomAccessFile randomAccessFile, final InputStream inputStream, final int n, final byte[] array) {
        int i;
        int read;
        for (i = 0; i < n; i += read) {
            read = inputStream.read(array, 0, Math.min(array.length, n - i));
            if (read == -1) {
                break;
            }
            randomAccessFile.write(array, 0, read);
        }
        return i;
    }
    
    public static void dumbDeleteRecursive(final File file) {
        Label_0040: {
            if (!file.isDirectory()) {
                break Label_0040;
            }
            final File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    dumbDeleteRecursive(listFiles[i]);
                }
                break Label_0040;
            }
            return;
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("could not delete: " + file);
        }
    }
    
    public static void fallocateIfSupported(final FileDescriptor fileDescriptor, final long n) {
        if (Build$VERSION.SDK_INT >= 21) {
            SysUtil$LollipopSysdeps.fallocateIfSupported(fileDescriptor, n);
        }
    }
    
    public static int findAbiScore(final String[] array, final String s) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && s.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }
    
    static void fsyncRecursive(final File file) {
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("cannot list directory " + file);
            }
            for (int i = 0; i < listFiles.length; ++i) {
                fsyncRecursive(listFiles[i]);
            }
        }
        else if (!file.getPath().endsWith("_lock")) {
            final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            final Throwable t = null;
            while (true) {
                try {
                    randomAccessFile.getFD().sync();
                    if (randomAccessFile != null) {
                        if (false) {
                            try {
                                randomAccessFile.close();
                                return;
                            }
                            catch (Throwable t2) {
                                throw new NullPointerException();
                            }
                        }
                        randomAccessFile.close();
                    }
                }
                catch (Throwable t) {
                    try {
                        throw t;
                    }
                    finally {}
                    while (true) {
                        if (randomAccessFile == null) {
                            break Label_0140;
                        }
                        Label_0151: {
                            if (t == null) {
                                break Label_0151;
                            }
                            try {
                                randomAccessFile.close();
                                throw file;
                            }
                            catch (Throwable randomAccessFile) {
                                t.addSuppressed((Throwable)randomAccessFile);
                                throw file;
                            }
                        }
                        randomAccessFile.close();
                        continue;
                    }
                }
                finally {
                    continue;
                }
                break;
            }
        }
    }
    
    public static String[] getSupportedAbis() {
        if (Build$VERSION.SDK_INT < 21) {
            return new String[] { Build.CPU_ABI, Build.CPU_ABI2 };
        }
        return SysUtil$LollipopSysdeps.getSupportedAbis();
    }
    
    public static byte[] makeApkDepBlock(final File file) {
        final Parcel obtain = Parcel.obtain();
        obtain.writeByte((byte)1);
        obtain.writeString(file.getPath());
        obtain.writeLong(file.lastModified());
        final byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
    
    static void mkdirOrThrow(final File file) {
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException("cannot mkdir: " + file);
        }
    }
}
