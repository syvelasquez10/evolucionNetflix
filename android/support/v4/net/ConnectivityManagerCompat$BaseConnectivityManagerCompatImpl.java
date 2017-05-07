// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;

class ConnectivityManagerCompat$BaseConnectivityManagerCompatImpl implements ConnectivityManagerCompat$ConnectivityManagerCompatImpl
{
    @Override
    public boolean isActiveNetworkMetered(final ConnectivityManager connectivityManager) {
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            switch (activeNetworkInfo.getType()) {
                case 0: {
                    break;
                }
                default: {
                    return true;
                }
                case 1: {
                    return false;
                }
            }
        }
        return true;
    }
}
