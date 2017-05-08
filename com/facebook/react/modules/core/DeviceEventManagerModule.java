// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import android.net.Uri;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class DeviceEventManagerModule extends ReactContextBaseJavaModule
{
    private final Runnable mInvokeDefaultBackPressRunnable;
    
    public DeviceEventManagerModule(final ReactApplicationContext reactApplicationContext, final DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        super(reactApplicationContext);
        this.mInvokeDefaultBackPressRunnable = new DeviceEventManagerModule$1(this, defaultHardwareBackBtnHandler);
    }
    
    public void emitHardwareBackPressed() {
        this.getReactApplicationContext().getJSModule(DeviceEventManagerModule$RCTDeviceEventEmitter.class).emit("hardwareBackPress", null);
    }
    
    public void emitNewIntentReceived(final Uri uri) {
        final WritableMap map = Arguments.createMap();
        map.putString("url", uri.toString());
        this.getReactApplicationContext().getJSModule(DeviceEventManagerModule$RCTDeviceEventEmitter.class).emit("url", map);
    }
    
    @Override
    public String getName() {
        return "DeviceEventManager";
    }
    
    @ReactMethod
    public void invokeDefaultBackPressHandler() {
        this.getReactApplicationContext().runOnUiQueueThread(this.mInvokeDefaultBackPressRunnable);
    }
}
