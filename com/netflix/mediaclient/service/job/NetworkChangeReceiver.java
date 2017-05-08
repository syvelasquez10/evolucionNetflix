// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class NetworkChangeReceiver extends BroadcastReceiver
{
    private static final String TAG = "nf_NetworkChangeReceiver";
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            if (context.getResources().getBoolean(2131558404)) {
                NetflixJobSchedulerPreL.onNetworkConnectivityChanged(context);
            }
            else if (Log.isLoggable()) {
                Log.e("nf_NetworkChangeReceiver", "onReceive networkChangeReceiverOn=false");
            }
        }
        else if (Log.isLoggable()) {
            Log.e("nf_NetworkChangeReceiver", "onReceive not from connectivityManager");
        }
    }
}
