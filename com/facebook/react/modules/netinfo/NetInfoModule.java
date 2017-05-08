// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.netinfo;

import android.support.v4.net.ConnectivityManagerCompat;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import android.net.ConnectivityManager;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class NetInfoModule extends ReactContextBaseJavaModule implements LifecycleEventListener
{
    private static final String CONNECTION_TYPE_NONE = "NONE";
    private static final String CONNECTION_TYPE_UNKNOWN = "UNKNOWN";
    private static final String ERROR_MISSING_PERMISSION = "E_MISSING_PERMISSION";
    private static final String MISSING_PERMISSION_MESSAGE = "To use NetInfo on Android, add the following to your AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />";
    private String mConnectivity;
    private final NetInfoModule$ConnectivityBroadcastReceiver mConnectivityBroadcastReceiver;
    private final ConnectivityManager mConnectivityManager;
    private boolean mNoNetworkPermission;
    
    public NetInfoModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mNoNetworkPermission = false;
        this.mConnectivity = "";
        this.mConnectivityManager = (ConnectivityManager)reactApplicationContext.getSystemService("connectivity");
        this.mConnectivityBroadcastReceiver = new NetInfoModule$ConnectivityBroadcastReceiver(this, null);
    }
    
    private WritableMap createConnectivityEventMap() {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("network_info", this.mConnectivity);
        return writableNativeMap;
    }
    
    private String getCurrentConnectionType() {
        try {
            final NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    if (ConnectivityManager.isNetworkTypeValid(activeNetworkInfo.getType())) {
                        return activeNetworkInfo.getTypeName().toUpperCase();
                    }
                    return "UNKNOWN";
                }
            }
        }
        catch (SecurityException ex) {
            this.mNoNetworkPermission = true;
            return "UNKNOWN";
        }
        return "NONE";
    }
    
    private void registerReceiver() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.getReactApplicationContext().registerReceiver((BroadcastReceiver)this.mConnectivityBroadcastReceiver, intentFilter);
        this.mConnectivityBroadcastReceiver.setRegistered(true);
    }
    
    private void sendConnectivityChangedEvent() {
        this.getReactApplicationContext().getJSModule(DeviceEventManagerModule$RCTDeviceEventEmitter.class).emit("networkStatusDidChange", this.createConnectivityEventMap());
    }
    
    private void unregisterReceiver() {
        if (this.mConnectivityBroadcastReceiver.isRegistered()) {
            this.getReactApplicationContext().unregisterReceiver((BroadcastReceiver)this.mConnectivityBroadcastReceiver);
            this.mConnectivityBroadcastReceiver.setRegistered(false);
        }
    }
    
    private void updateAndSendConnectionType() {
        final String currentConnectionType = this.getCurrentConnectionType();
        if (!currentConnectionType.equalsIgnoreCase(this.mConnectivity)) {
            this.mConnectivity = currentConnectionType;
            this.sendConnectivityChangedEvent();
        }
    }
    
    @ReactMethod
    public void getCurrentConnectivity(final Promise promise) {
        if (this.mNoNetworkPermission) {
            promise.reject("E_MISSING_PERMISSION", "To use NetInfo on Android, add the following to your AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />", null);
            return;
        }
        promise.resolve(this.createConnectivityEventMap());
    }
    
    @Override
    public String getName() {
        return "NetInfo";
    }
    
    @Override
    public void initialize() {
        this.getReactApplicationContext().addLifecycleEventListener(this);
    }
    
    @ReactMethod
    public void isConnectionMetered(final Promise promise) {
        if (this.mNoNetworkPermission) {
            promise.reject("E_MISSING_PERMISSION", "To use NetInfo on Android, add the following to your AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />", null);
            return;
        }
        promise.resolve(ConnectivityManagerCompat.isActiveNetworkMetered(this.mConnectivityManager));
    }
    
    public void onHostDestroy() {
    }
    
    @Override
    public void onHostPause() {
        this.unregisterReceiver();
    }
    
    @Override
    public void onHostResume() {
        this.registerReceiver();
    }
}
