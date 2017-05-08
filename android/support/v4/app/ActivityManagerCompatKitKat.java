// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.ActivityManager;
import android.annotation.TargetApi;

@TargetApi(19)
class ActivityManagerCompatKitKat
{
    public static boolean isLowRamDevice(final ActivityManager activityManager) {
        return activityManager.isLowRamDevice();
    }
}
