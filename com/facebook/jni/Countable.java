// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.jni;

import com.facebook.soloader.SoLoader;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class Countable
{
    @DoNotStrip
    private long mInstance;
    
    static {
        SoLoader.loadLibrary("fb");
    }
    
    public Countable() {
        this.mInstance = 0L;
    }
    
    public native void dispose();
    
    @Override
    protected void finalize() {
        this.dispose();
        super.finalize();
    }
}
