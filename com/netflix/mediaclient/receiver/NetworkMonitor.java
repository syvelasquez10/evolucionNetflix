// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.receiver;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class NetworkMonitor extends BroadcastReceiver
{
    private static final String TAG = "nf_net";
    
    private void handleBatteryLow(final Context context, final Intent intent) {
        Log.d("nf_net", "Low battery alert ");
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            NetworkMonitorRepository.getInstance().connectivityChange(intent);
            return;
        }
        if ("android.intent.action.BATTERY_LOW".equals(intent.getAction())) {
            this.handleBatteryLow(context, intent);
            return;
        }
        Log.w("nf_net", "We received action that we were not expecting: " + intent.getAction());
    }
}
