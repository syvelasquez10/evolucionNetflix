// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.net.URLConnection;
import android.annotation.SuppressLint;
import java.io.File;
import android.os.Environment;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import com.netflix.mediaclient.Log;
import java.io.FileNotFoundException;
import java.io.Closeable;
import android.content.Context;

public final class FileUtils
{
    public static final int BYTES_PER_KB = 1024;
    public static final int BYTES_PER_MB = 1048576;
    private static final String TAG = "FileUtils";
    
    public static boolean copyFileFromAssetToFS(final Context context, final String s, final String s2, final boolean b) {
        final boolean b2 = false;
        boolean b3 = false;
        while (true) {
            try {
                final FileInputStream openFileInput = context.openFileInput(s2);
                if (openFileInput != null) {
                    b3 = true;
                }
                IoUtil.safeClose(openFileInput);
                if (b3 && !b) {
                    return false;
                }
            }
            catch (FileNotFoundException ex2) {
                IoUtil.safeClose(null);
                b3 = b2;
                continue;
            }
            finally {
                IoUtil.safeClose(null);
            }
            break;
        }
        final Context context2;
        if (b3 && b && !context2.deleteFile(s2)) {
            Log.e("FileUtils", "Failed to delete file");
        }
        Closeable closeable = null;
        Closeable openFileOutput = null;
        final Closeable closeable2 = null;
        Closeable closeable3 = null;
        Closeable closeable4 = closeable2;
        try {
            try {
                final FileOutputStream fileOutputStream = (FileOutputStream)(openFileOutput = context2.openFileOutput(s2, 0));
                closeable3 = closeable3;
                closeable = fileOutputStream;
                closeable4 = closeable2;
                final InputStream open = context2.getAssets().open(s);
                if (open == null) {
                    openFileOutput = fileOutputStream;
                    closeable3 = open;
                    closeable = fileOutputStream;
                    closeable4 = open;
                    Log.e("FileUtils", "IS is null");
                    return false;
                }
                openFileOutput = fileOutputStream;
                closeable3 = open;
                closeable = fileOutputStream;
                closeable4 = open;
                final byte[] array = new byte[1024];
                while (true) {
                    openFileOutput = fileOutputStream;
                    closeable3 = open;
                    closeable = fileOutputStream;
                    closeable4 = open;
                    final int read = open.read(array);
                    if (read == -1) {
                        break;
                    }
                    openFileOutput = fileOutputStream;
                    closeable3 = open;
                    closeable = fileOutputStream;
                    closeable4 = open;
                    fileOutputStream.write(array, 0, read);
                }
            }
            catch (Exception ex) {
                closeable = openFileOutput;
                closeable4 = closeable3;
                Log.e("FileUtils", "Failed to extract CA", ex);
                return false;
            }
            return true;
        }
        finally {
            IoUtil.safeClose(closeable);
            IoUtil.safeClose(closeable4);
        }
    }
    
    public static String download(final Context context, String openConnection) throws IOException {
        final Closeable closeable = null;
        final String substring = openConnection.substring(openConnection.lastIndexOf(47) + 1);
        final FileOutputStream outputStream = getOutputStream(context, substring, true);
        Object inputStream = closeable;
        try {
            openConnection = (String)new URL(openConnection).openConnection();
            inputStream = closeable;
            ((URLConnection)openConnection).setConnectTimeout(5000);
            inputStream = closeable;
            ((URLConnection)openConnection).setReadTimeout(5000);
            inputStream = closeable;
            ((URLConnection)openConnection).connect();
            inputStream = closeable;
            openConnection = (String)(inputStream = ((URLConnection)openConnection).getInputStream());
            final byte[] array = new byte[512];
            while (true) {
                inputStream = openConnection;
                final int read = ((InputStream)openConnection).read(array);
                if (read == -1) {
                    break;
                }
                inputStream = openConnection;
                outputStream.write(array, 0, read);
            }
        }
        finally {
            IoUtil.safeClose(outputStream);
            IoUtil.safeClose((Closeable)inputStream);
        }
        outputStream.flush();
        IoUtil.safeClose(outputStream);
        IoUtil.safeClose((Closeable)openConnection);
        final Context context2;
        return "file://" + context2.getFilesDir().getAbsolutePath() + "/" + substring;
    }
    
    @SuppressLint({ "WorldReadableFiles" })
    public static FileOutputStream getOutputStream(final Context context, final String s, final boolean b) throws FileNotFoundException, IOException {
        if (b) {
            return context.openFileOutput(s, 0);
        }
        final File file = new File(Environment.getExternalStorageDirectory(), s);
        Log.d("FileUtils", "File " + file.getAbsolutePath());
        if (!file.exists()) {
            file.createNewFile();
        }
        return new FileOutputStream(file);
    }
    
    public static void removeFilesFromFS(final Context context, final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            final File dir = context.getDir(array[i], 0);
            if (dir.exists()) {
                if (Log.isLoggable("FileUtils", 3)) {
                    Log.d("FileUtils", "File for removal found: " + dir.getAbsolutePath());
                }
                final boolean deleteFile = context.deleteFile(array[i]);
                if (Log.isLoggable("FileUtils", 3)) {
                    Log.d("FileUtils", "File " + array[i] + " is deleted " + deleteFile);
                }
            }
        }
    }
}
