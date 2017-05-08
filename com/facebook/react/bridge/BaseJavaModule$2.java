// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

final class BaseJavaModule$2 extends BaseJavaModule$ArgumentExtractor<Double>
{
    BaseJavaModule$2() {
        super(null);
    }
    
    @Override
    public Double extractArgument(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final ReadableNativeArray readableNativeArray, final int n) {
        return readableNativeArray.getDouble(n);
    }
}
