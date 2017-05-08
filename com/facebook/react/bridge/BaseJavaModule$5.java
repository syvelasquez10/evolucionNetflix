// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

final class BaseJavaModule$5 extends BaseJavaModule$ArgumentExtractor<String>
{
    BaseJavaModule$5() {
        super(null);
    }
    
    @Override
    public String extractArgument(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final ReadableNativeArray readableNativeArray, final int n) {
        return readableNativeArray.getString(n);
    }
}
