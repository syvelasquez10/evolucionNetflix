// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class NativeRunnable implements Runnable
{
    private final HybridData mHybridData;
    
    private NativeRunnable(final HybridData mHybridData) {
        this.mHybridData = mHybridData;
    }
    
    @Override
    public native void run();
}
