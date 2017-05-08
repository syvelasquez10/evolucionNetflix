// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.soloader.SoLoader;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class ProxyJavaScriptExecutor extends JavaScriptExecutor
{
    static {
        SoLoader.loadLibrary("reactnativejnifb");
    }
    
    private static native HybridData initHybrid(final JavaJSExecutor p0);
}
