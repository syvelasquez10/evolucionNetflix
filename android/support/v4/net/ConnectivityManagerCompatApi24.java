// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import android.net.ConnectivityManager;
import android.annotation.TargetApi;

@TargetApi(24)
class ConnectivityManagerCompatApi24
{
    public static int getRestrictBackgroundStatus(final ConnectivityManager connectivityManager) {
        return connectivityManager.getRestrictBackgroundStatus();
    }
}
