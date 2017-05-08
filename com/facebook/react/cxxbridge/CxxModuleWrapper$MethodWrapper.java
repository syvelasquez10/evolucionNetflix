// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.NativeModule$NativeMethod;

@DoNotStrip
class CxxModuleWrapper$MethodWrapper implements NativeModule$NativeMethod
{
    @DoNotStrip
    HybridData mHybridData;
    
    CxxModuleWrapper$MethodWrapper() {
        this.mHybridData = this.initHybrid();
    }
    
    @Override
    public native String getType();
    
    public native HybridData initHybrid();
    
    public native void invoke(final CatalystInstance p0, final ExecutorToken p1, final ReadableNativeArray p2);
}
