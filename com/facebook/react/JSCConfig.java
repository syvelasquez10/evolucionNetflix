// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.bridge.WritableNativeMap;

public interface JSCConfig
{
    public static final JSCConfig EMPTY = new JSCConfig$1();
    
    WritableNativeMap getConfigMap();
}
