// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import com.crittercism.internal.dw;
import java.io.File;
import android.content.Context;

public class CrittercismNDK
{
    private static final String ASSET_SO_FILE_NAME = "lib64libcrittercism-v3.crt";
    private static final String DST_SO_FILE_NAME = "lib64libcrittercism-v3.so";
    private static final String[] LEGACY_SO_FILE_NAMES;
    private static final String LIBRARY_NAME = "64libcrittercism-v3";
    private static boolean isNdkInstalled;
    
    static {
        CrittercismNDK.isNdkInstalled = false;
        LEGACY_SO_FILE_NAMES = new String[] { "libcrittercism-ndk.so", "libcrittercism-v3.so" };
    }
    
    public static boolean copyLibFromAssets(final Context context, final File file) {
        FileOutputStream fileOutputStream;
        InputStream jarredLibFileStream;
        try {
            final File parentFile = file.getParentFile();
            dw.d("copyLibFromAssets: creating dir: " + parentFile.getAbsolutePath());
            parentFile.mkdirs();
            dw.d("copyLibFromAssets: installing library into: " + file.getAbsolutePath());
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
            dw.a("Could not install breakpad library: " + ex.toString());
            return false;
        }
        jarredLibFileStream.close();
        fileOutputStream.close();
        dw.d("copyLibFromAssets: successful");
        return true;
    }
    
    public static boolean doNdkSharedLibrariesExist(final Context context) {
        try {
            getJarredLibFileStream(context);
            return true;
        }
        catch (IOException ex) {
            dw.c("doNdkSharedLibrariesExist: ", ex);
            return false;
        }
    }
    
    public static File getInstalledLibraryFile(final Context context) {
        return new File(context.getFilesDir().getAbsolutePath() + "/com.crittercism/lib/" + "lib64libcrittercism-v3.so");
    }
    
    public static InputStream getJarredLibFileStream(final Context context) {
        String string = "armeabi";
        final String property = System.getProperty("os.arch");
        dw.d("getJarredLibFileStream: os.arch: " + property);
        if (property.contains("v7")) {
            string = "armeabi" + "-v7a";
        }
        else if (property.equals("aarch64")) {
            string = "arm64-v8a";
        }
        final String string2 = string + "/lib64libcrittercism-v3.crt";
        dw.d("getJarredLibFileStream: openning input stream from: " + string2);
        return context.getAssets().open(string2);
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
                System.loadLibrary("64libcrittercism-v3");
            }
            catch (Throwable t2) {
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
        }
        catch (Throwable t) {
            dw.a(t);
            return;
        }
        dw.b("Unable to initialize NDK crash reporting.");
    }
    
    public static boolean isNdkCrashReportingInstalled() {
        return CrittercismNDK.isNdkInstalled;
    }
    
    public static boolean loadLibraryFromAssets(final Context context) {
        final File file = new File(context.getFilesDir(), "/com.crittercism/lib/");
        final File file2 = new File(file, "lib64libcrittercism-v3.so");
        if (!file2.exists()) {
            if (!copyLibFromAssets(context, file2)) {
                file2.delete();
                return false;
            }
            for (int i = 0; i < CrittercismNDK.LEGACY_SO_FILE_NAMES.length; ++i) {
                final File file3 = new File(file, CrittercismNDK.LEGACY_SO_FILE_NAMES[i]);
                String s;
                if (file3.exists()) {
                    s = "deleting";
                }
                else {
                    s = "not found";
                }
                dw.d("legacy lib: " + file3.getAbsolutePath() + ": " + s);
                file3.delete();
            }
        }
        try {
            System.load(file2.getAbsolutePath());
            return true;
        }
        catch (Throwable t) {
            dw.a("Unable to install NDK library: " + t.getMessage());
            dw.a(t);
            file2.delete();
            return false;
        }
    }
}
