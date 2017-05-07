// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import android.net.NetworkInfo;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build$VERSION;

public class ConnectivityManagerCompat
{
    private static final ConnectivityManagerCompat$ConnectivityManagerCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = new ConnectivityManagerCompat$JellyBeanConnectivityManagerCompatImpl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 13) {
            IMPL = new ConnectivityManagerCompat$HoneycombMR2ConnectivityManagerCompatImpl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 8) {
            IMPL = new ConnectivityManagerCompat$GingerbreadConnectivityManagerCompatImpl();
            return;
        }
        IMPL = new ConnectivityManagerCompat$BaseConnectivityManagerCompatImpl();
    }
    
    public static NetworkInfo getNetworkInfoFromBroadcast(final ConnectivityManager connectivityManager, final Intent intent) {
        final NetworkInfo networkInfo = (NetworkInfo)intent.getParcelableExtra("networkInfo");
        if (networkInfo != null) {
            return connectivityManager.getNetworkInfo(networkInfo.getType());
        }
        return null;
    }
    
    public static boolean isActiveNetworkMetered(final ConnectivityManager connectivityManager) {
        return ConnectivityManagerCompat.IMPL.isActiveNetworkMetered(connectivityManager);
    }
}
