// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.receiver;

import android.os.Bundle;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class BluetoothReceiver extends BroadcastReceiver
{
    private static final String TAG = "nf_bluetooth";
    
    private void handleBluetoothConnectionStateChange(final Context context) {
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (!"android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
            Log.w("nf_bluetooth", "We received action that we were not expecting: " + intent.getAction());
            return;
        }
        final Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.getInt("android.bluetooth.adapter.extra.CONNECTION_STATE") == 2 || extras.getInt("android.bluetooth.adapter.extra.CONNECTION_STATE") == 0) {
                Log.d("nf_bluetooth", "ACTION_STATE_CHANGED - EXTRA_CONNECTION_STATE = " + extras.getInt("android.bluetooth.adapter.extra.CONNECTION_STATE"));
                this.handleBluetoothConnectionStateChange(context);
            }
            return;
        }
        Log.d("nf_bluetooth", "extras null - ignoring event");
    }
}
