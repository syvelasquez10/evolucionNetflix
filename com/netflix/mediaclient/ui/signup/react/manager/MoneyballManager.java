// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup.react.manager;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class MoneyballManager extends ReactContextBaseJavaModule
{
    public MoneyballManager(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    @ReactMethod
    public void execute(final ReadableMap readableMap, final Promise promise) {
        promise.resolve(Arguments.createMap());
    }
    
    @Override
    public String getName() {
        return "MoneyballManager";
    }
    
    @ReactMethod
    public void tokenActivate(final boolean b) {
    }
}
