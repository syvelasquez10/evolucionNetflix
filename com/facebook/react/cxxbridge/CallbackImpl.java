// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeArray;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Callback;

@DoNotStrip
public class CallbackImpl implements Callback
{
    @DoNotStrip
    private final HybridData mHybridData;
    
    private CallbackImpl(final HybridData mHybridData) {
        this.mHybridData = mHybridData;
    }
    
    private native void nativeInvoke(final NativeArray p0);
    
    @Override
    public void invoke(final Object... array) {
        this.nativeInvoke(Arguments.fromJavaArgs(array));
    }
}
