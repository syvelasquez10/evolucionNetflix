// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import com.facebook.react.bridge.WritableMap;
import java.net.SocketTimeoutException;
import java.io.IOException;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;

public class ResponseUtil
{
    public static void onDataReceived(final DeviceEventManagerModule$RCTDeviceEventEmitter deviceEventManagerModule$RCTDeviceEventEmitter, final int n, final String s) {
        final WritableArray array = Arguments.createArray();
        array.pushInt(n);
        array.pushString(s);
        deviceEventManagerModule$RCTDeviceEventEmitter.emit("didReceiveNetworkData", array);
    }
    
    public static void onDataReceivedProgress(final DeviceEventManagerModule$RCTDeviceEventEmitter deviceEventManagerModule$RCTDeviceEventEmitter, final int n, final long n2, final long n3) {
        final WritableArray array = Arguments.createArray();
        array.pushInt(n);
        array.pushInt((int)n2);
        array.pushInt((int)n3);
        deviceEventManagerModule$RCTDeviceEventEmitter.emit("didReceiveNetworkDataProgress", array);
    }
    
    public static void onDataSend(final DeviceEventManagerModule$RCTDeviceEventEmitter deviceEventManagerModule$RCTDeviceEventEmitter, final int n, final long n2, final long n3) {
        final WritableArray array = Arguments.createArray();
        array.pushInt(n);
        array.pushInt((int)n2);
        array.pushInt((int)n3);
        deviceEventManagerModule$RCTDeviceEventEmitter.emit("didSendNetworkData", array);
    }
    
    public static void onIncrementalDataReceived(final DeviceEventManagerModule$RCTDeviceEventEmitter deviceEventManagerModule$RCTDeviceEventEmitter, final int n, final String s, final long n2, final long n3) {
        final WritableArray array = Arguments.createArray();
        array.pushInt(n);
        array.pushString(s);
        array.pushInt((int)n2);
        array.pushInt((int)n3);
        deviceEventManagerModule$RCTDeviceEventEmitter.emit("didReceiveNetworkIncrementalData", array);
    }
    
    public static void onRequestError(final DeviceEventManagerModule$RCTDeviceEventEmitter deviceEventManagerModule$RCTDeviceEventEmitter, final int n, final String s, final IOException ex) {
        final WritableArray array = Arguments.createArray();
        array.pushInt(n);
        array.pushString(s);
        if (ex != null && ex.getClass() == SocketTimeoutException.class) {
            array.pushBoolean(true);
        }
        deviceEventManagerModule$RCTDeviceEventEmitter.emit("didCompleteNetworkResponse", array);
    }
    
    public static void onRequestSuccess(final DeviceEventManagerModule$RCTDeviceEventEmitter deviceEventManagerModule$RCTDeviceEventEmitter, final int n) {
        final WritableArray array = Arguments.createArray();
        array.pushInt(n);
        array.pushNull();
        deviceEventManagerModule$RCTDeviceEventEmitter.emit("didCompleteNetworkResponse", array);
    }
    
    public static void onResponseReceived(final DeviceEventManagerModule$RCTDeviceEventEmitter deviceEventManagerModule$RCTDeviceEventEmitter, final int n, final int n2, final WritableMap writableMap, final String s) {
        final WritableArray array = Arguments.createArray();
        array.pushInt(n);
        array.pushInt(n2);
        array.pushMap(writableMap);
        array.pushString(s);
        deviceEventManagerModule$RCTDeviceEventEmitter.emit("didReceiveNetworkResponse", array);
    }
}
