// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.jni;

import com.facebook.soloader.SoLoader;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class ThreadScopeSupport
{
    static {
        SoLoader.loadLibrary("fb");
    }
    
    @DoNotStrip
    private static void runStdFunction(final long n) {
        runStdFunctionImpl(n);
    }
    
    private static native void runStdFunctionImpl(final long p0);
}
