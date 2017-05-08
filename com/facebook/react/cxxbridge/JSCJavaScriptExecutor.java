// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.soloader.SoLoader;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class JSCJavaScriptExecutor extends JavaScriptExecutor
{
    static {
        SoLoader.loadLibrary("reactnativejnifb");
    }
    
    public JSCJavaScriptExecutor(final ReadableNativeArray readableNativeArray) {
        super(initHybrid(readableNativeArray));
    }
    
    private static native HybridData initHybrid(final ReadableNativeArray p0);
}
