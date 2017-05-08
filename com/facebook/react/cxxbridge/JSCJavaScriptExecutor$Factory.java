// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ReadableNativeArray;

public class JSCJavaScriptExecutor$Factory implements JavaScriptExecutor$Factory
{
    private ReadableNativeArray mJSCConfig;
    
    public JSCJavaScriptExecutor$Factory(final WritableNativeMap writableNativeMap) {
        final WritableNativeArray mjscConfig = new WritableNativeArray();
        mjscConfig.pushMap(writableNativeMap);
        this.mJSCConfig = mjscConfig;
    }
    
    @Override
    public JavaScriptExecutor create() {
        return new JSCJavaScriptExecutor(this.mJSCConfig);
    }
}
