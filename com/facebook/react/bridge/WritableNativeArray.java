// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class WritableNativeArray extends ReadableNativeArray implements WritableArray
{
    static {
        ReactBridge.staticInit();
    }
    
    public WritableNativeArray() {
        super(initHybrid());
    }
    
    private static native HybridData initHybrid();
    
    private native void pushNativeArray(final WritableNativeArray p0);
    
    private native void pushNativeMap(final WritableNativeMap p0);
    
    @Override
    public void pushArray(final WritableArray writableArray) {
        Assertions.assertCondition(writableArray == null || writableArray instanceof WritableNativeArray, "Illegal type provided");
        this.pushNativeArray((WritableNativeArray)writableArray);
    }
    
    @Override
    public native void pushBoolean(final boolean p0);
    
    public native void pushDouble(final double p0);
    
    @Override
    public native void pushInt(final int p0);
    
    @Override
    public void pushMap(final WritableMap writableMap) {
        Assertions.assertCondition(writableMap == null || writableMap instanceof WritableNativeMap, "Illegal type provided");
        this.pushNativeMap((WritableNativeMap)writableMap);
    }
    
    @Override
    public native void pushNull();
    
    @Override
    public native void pushString(final String p0);
}
