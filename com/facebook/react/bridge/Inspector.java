// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class Inspector
{
    static {
        ReactBridge.staticInit();
    }
    
    private native Inspector$LocalConnection connectNative(final int p0, final Inspector$RemoteConnection p1);
    
    private native Inspector$Page[] getPagesNative();
    
    private static native Inspector instance();
}
