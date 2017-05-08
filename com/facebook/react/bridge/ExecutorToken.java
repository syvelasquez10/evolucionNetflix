// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class ExecutorToken
{
    private final HybridData mHybridData;
    
    private ExecutorToken(final HybridData mHybridData) {
        this.mHybridData = mHybridData;
    }
}
