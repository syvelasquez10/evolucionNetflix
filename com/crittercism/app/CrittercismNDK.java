// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import android.util.Log;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import android.content.Context;

public class CrittercismNDK
{
    private static final String LIBNAME = "libcrittercism-ndk.so";
    private static final int LIBRARY_VERSION = 2;
    
    public static native boolean checkLibraryVersion(final int p0);
    
    public static boolean doNdkSharedLibrariesExist(final Context context) {
        try {
            getJarredLibFileStream(context);
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }
    
    public static File getInstalledLibraryFile(final Context context) {
        return new File(context.getFilesDir().getAbsolutePath() + "/com.crittercism/lib/" + "libcrittercism-ndk.so");
    }
    
    public static InputStream getJarredLibFileStream(final Context context) {
        String string = "armeabi";
        if (System.getProperty("os.arch").contains("v7")) {
            string = "armeabi" + "-v7a";
        }
        return context.getAssets().open(string + "/libcrittercism-ndk.so");
    }
    
    public static boolean installLib(final Context context, final File file) {
        if (file.exists()) {
            return true;
        }
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
            Log.e("Crittercism", "Could not install breakpad library: " + ex.toString());
            return false;
        }
        jarredLibFileStream.close();
        fileOutputStream.close();
        return true;
    }
    
    public static native boolean installNdk(final String p0);
    
    public static void installNdkLib(final Context t, String string) {
        string = ((Context)t).getFilesDir().getAbsolutePath() + "/" + string;
        int checkLibraryVersion = 0;
        if (!doNdkSharedLibrariesExist((Context)t)) {
            Log.e("Crittercism", "Trying to install Crittercism ndk on an sdkonly library");
        }
        else {
            final File installedLibraryFile = getInstalledLibraryFile((Context)t);
            if (installLib((Context)t, installedLibraryFile)) {
                while (true) {
                    try {
                        System.load(installedLibraryFile.getAbsolutePath());
                        checkLibraryVersion = (checkLibraryVersion(2) ? 1 : 0);
                        Label_0099: {
                            if (checkLibraryVersion != 0) {
                                break Label_0099;
                            }
                            if (!installLib((Context)t, installedLibraryFile)) {
                                return;
                            }
                            try {
                                System.load(installedLibraryFile.getAbsolutePath());
                                try {
                                    if (installNdk(string)) {
                                        final File file = new File(string);
                                        file.getAbsolutePath();
                                        file.mkdirs();
                                    }
                                }
                                catch (Throwable t2) {}
                            }
                            catch (Throwable t) {
                                Log.e("Crittercism", "Unable to load breakpad" + t.toString());
                            }
                        }
                    }
                    catch (Throwable t3) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
}
