// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

abstract class BaseJavaModule$ArgumentExtractor<T>
{
    public abstract T extractArgument(final CatalystInstance p0, final ExecutorToken p1, final ReadableNativeArray p2, final int p3);
    
    public int getJSArgumentsNeeded() {
        return 1;
    }
}
