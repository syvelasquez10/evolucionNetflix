// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.appstate;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class AppStateModule extends ReactContextBaseJavaModule implements LifecycleEventListener
{
    public static final String APP_STATE_ACTIVE = "active";
    public static final String APP_STATE_BACKGROUND = "background";
    private String mAppState;
    
    public AppStateModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mAppState = "uninitialized";
    }
    
    private WritableMap createAppStateEventMap() {
        final WritableMap map = Arguments.createMap();
        map.putString("app_state", this.mAppState);
        return map;
    }
    
    private void sendAppStateChangeEvent() {
        this.getReactApplicationContext().getJSModule(DeviceEventManagerModule$RCTDeviceEventEmitter.class).emit("appStateDidChange", this.createAppStateEventMap());
    }
    
    @ReactMethod
    public void getCurrentAppState(final Callback callback, final Callback callback2) {
        callback.invoke(this.createAppStateEventMap());
    }
    
    @Override
    public String getName() {
        return "AppState";
    }
    
    @Override
    public void initialize() {
        this.getReactApplicationContext().addLifecycleEventListener(this);
    }
    
    public void onHostDestroy() {
    }
    
    @Override
    public void onHostPause() {
        this.mAppState = "background";
        this.sendAppStateChangeEvent();
    }
    
    @Override
    public void onHostResume() {
        this.mAppState = "active";
        this.sendAppStateChangeEvent();
    }
}
