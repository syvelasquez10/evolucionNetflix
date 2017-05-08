// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.netinfo;

import android.support.v4.net.ConnectivityManagerCompat;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import android.net.ConnectivityManager;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class NetInfoModule$ConnectivityBroadcastReceiver extends BroadcastReceiver
{
    private boolean isRegistered;
    final /* synthetic */ NetInfoModule this$0;
    
    private NetInfoModule$ConnectivityBroadcastReceiver(final NetInfoModule this$0) {
        this.this$0 = this$0;
        this.isRegistered = false;
    }
    
    public boolean isRegistered() {
        return this.isRegistered;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            this.this$0.updateAndSendConnectionType();
        }
    }
    
    public void setRegistered(final boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
}
