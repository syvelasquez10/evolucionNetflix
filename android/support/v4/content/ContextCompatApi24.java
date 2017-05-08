// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import java.io.File;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(24)
class ContextCompatApi24
{
    public static Context createDeviceProtectedStorageContext(final Context context) {
        return context.createDeviceProtectedStorageContext();
    }
    
    public static File getDataDir(final Context context) {
        return context.getDataDir();
    }
    
    public static boolean isDeviceProtectedStorage(final Context context) {
        return context.isDeviceProtectedStorage();
    }
}
