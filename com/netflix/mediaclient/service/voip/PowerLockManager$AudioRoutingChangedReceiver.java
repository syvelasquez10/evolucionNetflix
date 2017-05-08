// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import android.content.IntentFilter;
import android.media.AudioManager;
import android.annotation.TargetApi;
import com.netflix.mediaclient.util.DeviceCategory;
import android.os.PowerManager;
import android.os.PowerManager$WakeLock;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class PowerLockManager$AudioRoutingChangedReceiver extends BroadcastReceiver
{
    final /* synthetic */ PowerLockManager this$0;
    
    private PowerLockManager$AudioRoutingChangedReceiver(final PowerLockManager this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        if ("android.intent.action.HEADSET_PLUG".equals(action)) {
            Log.d("nf_voip", "Wired headset state changed...");
            this.this$0.handleStateChange();
        }
        else if ("android.bluetooth.device.action.ACL_CONNECTED".equals(action) || "android.bluetooth.device.action.ACL_DISCONNECTED".equals(action)) {
            Log.d("nf_voip", "Bluetooth headset state changed...");
            this.this$0.handleStateChange();
        }
    }
}
