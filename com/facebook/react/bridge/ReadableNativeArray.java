// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class ReadableNativeArray extends NativeArray implements ReadableArray
{
    static {
        ReactBridge.staticInit();
    }
    
    protected ReadableNativeArray(final HybridData hybridData) {
        super(hybridData);
    }
    
    @Override
    public native ReadableNativeArray getArray(final int p0);
    
    @Override
    public native boolean getBoolean(final int p0);
    
    @Override
    public native double getDouble(final int p0);
    
    @Override
    public native int getInt(final int p0);
    
    @Override
    public native ReadableNativeMap getMap(final int p0);
    
    @Override
    public native String getString(final int p0);
    
    public native ReadableType getType(final int p0);
    
    public native boolean isNull(final int p0);
    
    @Override
    public native int size();
}
