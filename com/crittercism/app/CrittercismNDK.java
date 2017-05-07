// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import crittercism.android.dy;
import java.io.File;
import android.content.Context;

public class CrittercismNDK
{
    private static boolean isNdkInstalled;
    
    static {
        CrittercismNDK.isNdkInstalled = false;
    }
    
    public static boolean copyLibFromAssets(final Context context, final File file) {
        dy.b();
        FileOutputStream fileOutputStream;
        InputStream jarredLibFileStream;
        try {
            file.getParentFile().mkdirs();
            fileOutputStream = new FileOutputStream(file);
            jarredLibFileStream = getJarredLibFileStream(context);
            final byte[] array = new byte[8192];
            while (true) {
                final int read = jarredLibFileStream.read(array);
                if (read < 0) {
                    break;
                }
                fileOutputStream.write(array, 0, read);
            }
        }
        catch (Exception ex) {
            dy.b("Could not install breakpad library: " + ex.toString());
            return false;
        }
        jarredLibFileStream.close();
        fileOutputStream.close();
        return true;
    }
    
    public static boolean doNdkSharedLibrariesExist(final Context context) {
        try {
            getJarredLibFileStream(context);
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }
    
    public static InputStream getJarredLibFileStream(final Context context) {
        String string = "armeabi";
        if (System.getProperty("os.arch").contains("v7")) {
            string = "armeabi" + "-v7a";
        }
        return context.getAssets().open(string + "/libcrittercism-v3.so");
    }
    
    public static native boolean installNdk(final String p0);
    
    public static void installNdkLib(final Context context, String string) {
        int loadLibraryFromAssets = 1;
        string = context.getFilesDir().getAbsolutePath() + "/" + string;
        if (doNdkSharedLibrariesExist(context)) {
            loadLibraryFromAssets = (loadLibraryFromAssets(context) ? 1 : 0);
        }
        else {
            try {
                System.loadLibrary("crittercism-v3");
            }
            catch (Throwable t) {
                loadLibraryFromAssets = 0;
            }
        }
        if (loadLibraryFromAssets == 0) {
            return;
        }
        try {
            if (installNdk(string)) {
                new File(string).mkdirs();
                CrittercismNDK.isNdkInstalled = true;
                return;
            }
            dy.c("Unable to initialize NDK crash reporting.");
        }
        catch (Throwable t2) {}
    }
    
    public static boolean loadLibraryFromAssets(final Context context) {
        final File file = new File(context.getFilesDir(), "/com.crittercism/lib/");
        final File file2 = new File(file, "libcrittercism-v3.so");
        final File file3 = new File(file, "libcrittercism-ndk.so");
        if (!file2.exists()) {
            if (!copyLibFromAssets(context, file2)) {
                file2.delete();
                return false;
            }
            file3.delete();
        }
        try {
            System.load(file2.getAbsolutePath());
            return true;
        }
        catch (Throwable t) {
            dy.a("Unable to install NDK library", t);
            file2.delete();
            return false;
        }
    }
}
