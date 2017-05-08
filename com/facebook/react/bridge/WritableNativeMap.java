// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class WritableNativeMap extends ReadableNativeMap implements WritableMap
{
    static {
        ReactBridge.staticInit();
    }
    
    public WritableNativeMap() {
        super(initHybrid());
    }
    
    private static native HybridData initHybrid();
    
    private native void mergeNativeMap(final ReadableNativeMap p0);
    
    private native void putNativeArray(final String p0, final WritableNativeArray p1);
    
    private native void putNativeMap(final String p0, final WritableNativeMap p1);
    
    @Override
    public void putArray(final String s, final WritableArray writableArray) {
        Assertions.assertCondition(writableArray == null || writableArray instanceof WritableNativeArray, "Illegal type provided");
        this.putNativeArray(s, (WritableNativeArray)writableArray);
    }
    
    @Override
    public native void putBoolean(final String p0, final boolean p1);
    
    @Override
    public native void putDouble(final String p0, final double p1);
    
    @Override
    public native void putInt(final String p0, final int p1);
    
    @Override
    public void putMap(final String s, final WritableMap writableMap) {
        Assertions.assertCondition(writableMap == null || writableMap instanceof WritableNativeMap, "Illegal type provided");
        this.putNativeMap(s, (WritableNativeMap)writableMap);
    }
    
    public native void putNull(final String p0);
    
    @Override
    public native void putString(final String p0, final String p1);
}
