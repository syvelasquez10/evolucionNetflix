// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

final class BaseJavaModule$6 extends BaseJavaModule$ArgumentExtractor<ReadableNativeArray>
{
    BaseJavaModule$6() {
        super(null);
    }
    
    @Override
    public ReadableNativeArray extractArgument(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final ReadableNativeArray readableNativeArray, final int n) {
        return readableNativeArray.getArray(n);
    }
}
