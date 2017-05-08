// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.location;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.SystemClock;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import android.os.Build$VERSION;
import com.facebook.react.bridge.Arguments;
import android.location.LocationManager;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import android.os.Bundle;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import android.location.Location;
import android.location.LocationListener;

class LocationModule$1 implements LocationListener
{
    final /* synthetic */ LocationModule this$0;
    
    LocationModule$1(final LocationModule this$0) {
        this.this$0 = this$0;
    }
    
    public void onLocationChanged(final Location location) {
        this.this$0.getReactApplicationContext().getJSModule(DeviceEventManagerModule$RCTDeviceEventEmitter.class).emit("geolocationDidChange", locationToMap(location));
    }
    
    public void onProviderDisabled(final String s) {
    }
    
    public void onProviderEnabled(final String s) {
    }
    
    public void onStatusChanged(final String s, final int n, final Bundle bundle) {
        if (n == 0) {
            this.this$0.emitError(PositionError.POSITION_UNAVAILABLE, "Provider " + s + " is out of service.");
        }
        else if (n == 1) {
            this.this$0.emitError(PositionError.TIMEOUT, "Provider " + s + " is temporarily unavailable.");
        }
    }
}
