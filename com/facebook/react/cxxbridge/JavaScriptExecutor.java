// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public abstract class JavaScriptExecutor
{
    private final HybridData mHybridData;
    
    protected JavaScriptExecutor(final HybridData mHybridData) {
        this.mHybridData = mHybridData;
    }
}
