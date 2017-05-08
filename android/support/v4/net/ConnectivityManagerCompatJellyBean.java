// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import android.net.ConnectivityManager;
import android.annotation.TargetApi;

@TargetApi(16)
class ConnectivityManagerCompatJellyBean
{
    public static boolean isActiveNetworkMetered(final ConnectivityManager connectivityManager) {
        return connectivityManager.isActiveNetworkMetered();
    }
}
