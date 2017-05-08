// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.os.Bundle;
import android.content.Intent;
import android.os.Environment;
import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.os.Build$VERSION;
import android.util.Log;
import android.support.v4.os.BuildCompat;
import android.os.Process;
import android.content.Context;
import java.io.File;
import android.util.TypedValue;

public class ContextCompat
{
    private static final String DIR_ANDROID = "Android";
    private static final String DIR_OBB = "obb";
    private static final String TAG = "ContextCompat";
    private static final Object sLock;
    private static TypedValue sTempValue;
    
    static {
        sLock = new Object();
    }
    
    private static File buildPath(File file, final String... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final String s = array[i];
            if (file == null) {
                file = new File(s);
            }
            else if (s != null) {
                file = new File(file, s);
            }
        }
        return file;
    }
    
    public static int checkSelfPermission(final Context context, final String s) {
        if (s == null) {
            throw new IllegalArgumentException("permission is null");
        }
        return context.checkPermission(s, Process.myPid(), Process.myUid());
    }
    
    @Deprecated
    public static Context createDeviceEncryptedStorageContext(final Context context) {
        return createDeviceProtectedStorageContext(context);
    }
    
    public static Context createDeviceProtectedStorageContext(final Context context) {
        if (BuildCompat.isAtLeastN()) {
            return ContextCompatApi24.createDeviceProtectedStorageContext(context);
        }
        return null;
    }
    
    private static File createFilesDir(final File file) {
        // monitorenter(ContextCompat.class)
        File file2 = file;
        try {
            if (!file.exists()) {
                file2 = file;
                if (!file.mkdirs()) {
                    if (file.exists()) {
                        file2 = file;
                    }
                    else {
                        Log.w("ContextCompat", "Unable to create files subdir " + file.getPath());
                        file2 = null;
                    }
                }
            }
            return file2;
        }
        finally {
        }
        // monitorexit(ContextCompat.class)
    }
    
    public static File getCodeCacheDir(final Context context) {
        if (Build$VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getCodeCacheDir(context);
        }
        return createFilesDir(new File(context.getApplicationInfo().dataDir, "code_cache"));
    }
    
    public static final int getColor(final Context context, final int n) {
        if (Build$VERSION.SDK_INT >= 23) {
            return ContextCompatApi23.getColor(context, n);
        }
        return context.getResources().getColor(n);
    }
    
    public static final ColorStateList getColorStateList(final Context context, final int n) {
        if (Build$VERSION.SDK_INT >= 23) {
            return ContextCompatApi23.getColorStateList(context, n);
        }
        return context.getResources().getColorStateList(n);
    }
    
    public static File getDataDir(final Context context) {
        if (BuildCompat.isAtLeastN()) {
            return ContextCompatApi24.getDataDir(context);
        }
        final String dataDir = context.getApplicationInfo().dataDir;
        if (dataDir != null) {
            return new File(dataDir);
        }
        return null;
    }
    
    public static final Drawable getDrawable(final Context context, int resourceId) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            return ContextCompatApi21.getDrawable(context, resourceId);
        }
        if (sdk_INT >= 16) {
            return context.getResources().getDrawable(resourceId);
        }
        synchronized (ContextCompat.sLock) {
            if (ContextCompat.sTempValue == null) {
                ContextCompat.sTempValue = new TypedValue();
            }
            context.getResources().getValue(resourceId, ContextCompat.sTempValue, true);
            resourceId = ContextCompat.sTempValue.resourceId;
            // monitorexit(ContextCompat.sLock)
            return context.getResources().getDrawable(resourceId);
        }
    }
    
    public static File[] getExternalCacheDirs(final Context context) {
        if (Build$VERSION.SDK_INT >= 19) {
            return ContextCompatKitKat.getExternalCacheDirs(context);
        }
        return new File[] { context.getExternalCacheDir() };
    }
    
    public static File[] getExternalFilesDirs(final Context context, final String s) {
        if (Build$VERSION.SDK_INT >= 19) {
            return ContextCompatKitKat.getExternalFilesDirs(context, s);
        }
        return new File[] { context.getExternalFilesDir(s) };
    }
    
    public static final File getNoBackupFilesDir(final Context context) {
        if (Build$VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getNoBackupFilesDir(context);
        }
        return createFilesDir(new File(context.getApplicationInfo().dataDir, "no_backup"));
    }
    
    public static File[] getObbDirs(final Context context) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 19) {
            return ContextCompatKitKat.getObbDirs(context);
        }
        File file;
        if (sdk_INT >= 11) {
            file = ContextCompatHoneycomb.getObbDir(context);
        }
        else {
            file = buildPath(Environment.getExternalStorageDirectory(), "Android", "obb", context.getPackageName());
        }
        return new File[] { file };
    }
    
    @Deprecated
    public static boolean isDeviceEncryptedStorage(final Context context) {
        return isDeviceProtectedStorage(context);
    }
    
    public static boolean isDeviceProtectedStorage(final Context context) {
        return BuildCompat.isAtLeastN() && ContextCompatApi24.isDeviceProtectedStorage(context);
    }
    
    public static boolean startActivities(final Context context, final Intent[] array) {
        return startActivities(context, array, null);
    }
    
    public static boolean startActivities(final Context context, final Intent[] array, final Bundle bundle) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 16) {
            ContextCompatJellybean.startActivities(context, array, bundle);
            return true;
        }
        if (sdk_INT >= 11) {
            ContextCompatHoneycomb.startActivities(context, array);
            return true;
        }
        return false;
    }
}
