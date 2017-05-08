// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

final class BaseJavaModule$1 extends BaseJavaModule$ArgumentExtractor<Boolean>
{
    BaseJavaModule$1() {
        super(null);
    }
    
    @Override
    public Boolean extractArgument(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final ReadableNativeArray readableNativeArray, final int n) {
        return readableNativeArray.getBoolean(n);
    }
}
