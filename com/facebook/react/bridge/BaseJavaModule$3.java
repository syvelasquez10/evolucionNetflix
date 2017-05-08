// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

final class BaseJavaModule$3 extends BaseJavaModule$ArgumentExtractor<Float>
{
    BaseJavaModule$3() {
        super(null);
    }
    
    @Override
    public Float extractArgument(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final ReadableNativeArray readableNativeArray, final int n) {
        return (float)readableNativeArray.getDouble(n);
    }
}
