// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.jni;

import com.facebook.soloader.SoLoader;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class HybridData
{
    @DoNotStrip
    private long mNativePointer;
    
    static {
        SoLoader.loadLibrary("fb");
    }
    
    public HybridData() {
        this.mNativePointer = 0L;
    }
    
    @Override
    protected void finalize() {
        this.resetNative();
        super.finalize();
    }
    
    public native void resetNative();
}
