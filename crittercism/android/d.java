// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.net.NetworkInfo;
import android.content.Context;
import android.net.ConnectivityManager;

public final class d
{
    private ConnectivityManager a;
    
    public d(final Context context) {
        if (context == null) {
            dy.b("Given a null Context.");
            return;
        }
        if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) == 0) {
            this.a = (ConnectivityManager)context.getSystemService("connectivity");
            return;
        }
        dy.b("Add android.permission.ACCESS_NETWORK_STATE to AndroidManifest.xml to get more detailed OPTMZ data");
    }
    
    public final b a() {
        if (this.a == null) {
            return b.c;
        }
        final NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return b.d;
        }
        return b.a(activeNetworkInfo.getType());
    }
    
    public final String b() {
        if (this.a == null) {
            return "unknown";
        }
        final NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "disconnected";
        }
        final int type = activeNetworkInfo.getType();
        if (type == 0) {
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11: {
                    return "2G";
                }
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15: {
                    return "3G";
                }
                case 13: {
                    return "LTE";
                }
            }
        }
        else if (type == 1) {
            return "wifi";
        }
        return "unknown";
    }
}
