// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup.react.manager;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class LoggingManager extends ReactContextBaseJavaModule
{
    public LoggingManager(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    @ReactMethod
    public void beginCommand(final String s, final ReadableMap readableMap) {
    }
    
    @ReactMethod
    public void endCommand(final String s, final ReadableMap readableMap) {
    }
    
    @Override
    public String getName() {
        return "LoggingManager";
    }
    
    @ReactMethod
    public void pushNavigationWithData(final ReadableMap readableMap) {
    }
}
