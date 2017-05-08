// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

final class BaseJavaModule$4 extends BaseJavaModule$ArgumentExtractor<Integer>
{
    BaseJavaModule$4() {
        super(null);
    }
    
    @Override
    public Integer extractArgument(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final ReadableNativeArray readableNativeArray, final int n) {
        return (int)readableNativeArray.getDouble(n);
    }
}
