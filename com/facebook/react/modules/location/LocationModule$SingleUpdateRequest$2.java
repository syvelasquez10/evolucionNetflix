// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.location;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.SystemClock;
import com.facebook.react.bridge.ReadableMap;
import android.os.Build$VERSION;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import android.os.Looper;
import android.location.LocationManager;
import android.os.Handler;
import com.facebook.react.bridge.Callback;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;

class LocationModule$SingleUpdateRequest$2 implements LocationListener
{
    final /* synthetic */ LocationModule$SingleUpdateRequest this$0;
    
    LocationModule$SingleUpdateRequest$2(final LocationModule$SingleUpdateRequest this$0) {
        this.this$0 = this$0;
    }
    
    public void onLocationChanged(final Location location) {
        synchronized (this.this$0) {
            if (!this.this$0.mTriggered) {
                this.this$0.mSuccess.invoke(locationToMap(location));
                this.this$0.mHandler.removeCallbacks(this.this$0.mTimeoutRunnable);
                this.this$0.mTriggered = true;
            }
        }
    }
    
    public void onProviderDisabled(final String s) {
    }
    
    public void onProviderEnabled(final String s) {
    }
    
    public void onStatusChanged(final String s, final int n, final Bundle bundle) {
    }
}
