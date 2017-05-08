// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
class ReadableNativeMap$ReadableNativeMapKeySetIterator implements ReadableMapKeySetIterator
{
    @DoNotStrip
    private final HybridData mHybridData;
    @DoNotStrip
    private final ReadableNativeMap mMap;
    
    public ReadableNativeMap$ReadableNativeMapKeySetIterator(final ReadableNativeMap mMap) {
        this.mMap = mMap;
        this.mHybridData = initHybrid(mMap);
    }
    
    private static native HybridData initHybrid(final ReadableNativeMap p0);
    
    @Override
    public native boolean hasNextKey();
    
    @Override
    public native String nextKey();
}
