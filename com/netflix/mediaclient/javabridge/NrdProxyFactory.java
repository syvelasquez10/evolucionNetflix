// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge;

import com.netflix.mediaclient.javabridge.ui.android.NativeNrdProxy;

public class NrdProxyFactory
{
    public static NrdProxy createInstance(final Bridge bridge) {
        return new NativeNrdProxy(bridge);
    }
}
