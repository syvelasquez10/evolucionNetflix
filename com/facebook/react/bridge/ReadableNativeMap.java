// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class ReadableNativeMap extends NativeMap implements ReadableMap
{
    static {
        ReactBridge.staticInit();
    }
    
    protected ReadableNativeMap(final HybridData hybridData) {
        super(hybridData);
    }
    
    @Override
    public native ReadableNativeArray getArray(final String p0);
    
    @Override
    public native boolean getBoolean(final String p0);
    
    @Override
    public native double getDouble(final String p0);
    
    @Override
    public Dynamic getDynamic(final String s) {
        return DynamicFromMap.create(this, s);
    }
    
    @Override
    public native int getInt(final String p0);
    
    @Override
    public native ReadableNativeMap getMap(final String p0);
    
    @Override
    public native String getString(final String p0);
    
    @Override
    public native ReadableType getType(final String p0);
    
    @Override
    public native boolean hasKey(final String p0);
    
    @Override
    public native boolean isNull(final String p0);
    
    @Override
    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableNativeMap$ReadableNativeMapKeySetIterator(this);
    }
}
