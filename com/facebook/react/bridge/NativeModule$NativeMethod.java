// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public interface NativeModule$NativeMethod
{
    String getType();
    
    void invoke(final CatalystInstance p0, final ExecutorToken p1, final ReadableNativeArray p2);
}
